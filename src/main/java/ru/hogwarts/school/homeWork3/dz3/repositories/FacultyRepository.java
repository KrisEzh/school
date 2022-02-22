package ru.hogwarts.school.homeWork3.dz3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.homeWork3.dz3.models.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour);
}
