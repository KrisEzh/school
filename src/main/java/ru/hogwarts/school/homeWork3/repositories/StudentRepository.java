package ru.hogwarts.school.homeWork3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homeWork3.models.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAgeBetween(int age, int age2);

}
