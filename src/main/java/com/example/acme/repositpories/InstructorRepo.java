package com.example.acme.repositpories;

import com.example.acme.entities.Instructors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface InstructorRepo extends JpaRepository<Instructors, Long> {
    Instructors findByInstructorName(String name);
}
