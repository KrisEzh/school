package ru.hogwarts.school.homeWork3.lesson4.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.homeWork3.lesson4.model.Avatar;
import ru.hogwarts.school.homeWork3.lesson4.model.Student;
import ru.hogwarts.school.homeWork3.lesson4.repository.AvatarRepository;
import ru.hogwarts.school.homeWork3.lesson4.repository.StudentRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("avatars")
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;


    public AvatarService(StudentService studentService, AvatarRepository avatarRepository){
        this.studentService=studentService;
        this.avatarRepository=avatarRepository;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentService.findStudent(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public Avatar findAvatar(Long studentId){
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private byte[] generateImageData(Path filePath) throws IOException{
        try(InputStream is = Files.newInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            BufferedImage image = ImageIO.read(bis);
            int height = image.getHeight()/(image.getWidth()/100);
            BufferedImage data = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = data.createGraphics();
            graphics.drawImage(image, 0,0,100, height, null);
            graphics.dispose();

            ImageIO.write(data,getExtensions(filePath.getFileName().toString()),baos);
            return baos.toByteArray();
        }
    }

}