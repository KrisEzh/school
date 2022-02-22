package ru.hogwarts.school.homeWork3.lesson3;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAgeBetween(int age, int age2);

}

