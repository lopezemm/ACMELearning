package com.example.acme.repositpories;

import com.example.acme.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepo extends JpaRepository<Courses, Long> {
    Courses findByCourseName(String courseName);
}
