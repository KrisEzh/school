package ru.hogwarts.school.dz3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.dz3.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAgeBetween(int age, int age2);

}
