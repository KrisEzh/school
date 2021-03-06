package ru.hogwarts.school.homeWork3.lesson3;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public Student findStudent(long id){
        return studentRepository.findById(id).get();
    }
    public Student editStudent(Student student){
        return studentRepository.save(student);
    }
    public Student deleteStudent(long id){
        return studentRepository.getById(id);
    }
    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public List<Student> filterByAge(int age){
        return getAllStudents()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
    public Collection<Student> findByAgeBetween(int age, int age2){
        return studentRepository.findByAgeBetween(age, age2);

    }

}
