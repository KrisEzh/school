package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository){
        this.facultyRepository=facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id){
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public Faculty deleteFaculty(long id){
        return facultyRepository.getById(id);
    }
    public Collection<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
    public List<Faculty> filterByColour(String colour){
        return getAllFaculties()
                .stream()
                .filter(faculty -> faculty.getColour().equals(colour))
                .collect(Collectors.toList());
    }
}
