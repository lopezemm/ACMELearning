package com.example.acme.services;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Students;
import com.example.acme.repositpories.CourseRepo;
import com.example.acme.util.UtilValidations;
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
        Courses c = new Courses();
        c = courseRepo.save(course);
        return c.getCourseName();

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

    public String startCourse(Long courseId){
        Courses c = findCourse(courseId);
        if(null!=c) {
            c.setIsStarted("S");
            return this.addCourse(c);
        }
        return "not found";
    }

    public String cancelCourse(Long courseId){
        Courses c = findCourse(courseId);
        if(null!=c) {
            if(!UtilValidations.isStarted(c.getIsStarted())) {
                c.setIsStarted("C");
                return this.addCourse(c) + " has been canceled ";
            }else{
                return c.getCourseName() + " cannot be canceled as has been started";
            }
        }
        return "not found";
    }

    public Courses findCourse(Long courseId){
        Optional<Courses> opt = courseRepo.findById(courseId);
        if(opt.isPresent()){
            return opt.get();
        }else{
            return null;
        }
    }
}
