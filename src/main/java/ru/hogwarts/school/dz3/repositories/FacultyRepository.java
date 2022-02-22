package ru.hogwarts.school.dz3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.dz3.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour);
}
