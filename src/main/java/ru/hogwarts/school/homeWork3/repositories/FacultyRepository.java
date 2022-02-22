package ru.hogwarts.school.homeWork3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homeWork3.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour);
}
