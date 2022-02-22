package ru.hogwarts.school.homeWork3.lesson3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour);
}
