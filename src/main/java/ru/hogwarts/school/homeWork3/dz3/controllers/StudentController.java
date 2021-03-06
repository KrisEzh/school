package ru.hogwarts.school.homeWork3.dz3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.homeWork3.dz3.models.Student;
import ru.hogwarts.school.homeWork3.dz3.services.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam("min") int age, @RequestParam ("max") int age2){
        if(age != 0){
            return ResponseEntity.ok(studentService.findByAgeBetween(age, age2));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping(path="sort")
    public List<Student> filterByAge(@RequestParam("age") int age){
        return studentService.filterByAge(age);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        Student foundStudent = studentService.editStudent(student);
        if(foundStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
         studentService.deleteStudent(id);
         return ResponseEntity.ok().build();
    }
}
