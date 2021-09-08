package com.example.acme.repositpories;

import com.example.acme.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepo extends JpaRepository<Students, Long> {
    Students findByStudentName(String studentName);
}
