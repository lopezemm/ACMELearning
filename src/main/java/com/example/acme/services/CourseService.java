package com.example.acme.services;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Students;
import com.example.acme.repositpories.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public String addCourse(Courses course) {
        courseRepo.save(course);
        return courseRepo.findByCourseName(course.getCourseName()).getCourseName();

    }

    public boolean deleteCourse(long courseId) {
        Optional<Courses> opt = courseRepo.findById(courseId);
        if(opt.isPresent()){
            opt.ifPresent(c -> courseRepo.delete(c));
            return true;
        }else{
            return false;
        }

    }

    public List<Courses> getAllCourses() {
        return courseRepo.findAll();
    }
}
