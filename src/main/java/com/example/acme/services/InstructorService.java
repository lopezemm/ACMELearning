package com.example.acme.services;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Instructors;
import com.example.acme.entities.Students;
import com.example.acme.repositpories.CourseRepo;
import com.example.acme.repositpories.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    InstructorRepo instructorRepo;

    CourseRepo courseRepo;

    public InstructorService(InstructorRepo instructorRepo, CourseRepo courseRepo) {
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
    }

    public String addInstructor(Instructors instructor){
        instructorRepo.save(instructor);
        return instructorRepo.findByInstructorName(instructor.getInstructorName()).getInstructorName();

    }

    public Boolean deleteInstructor(Long instructorId){
        Optional<Instructors> opt = instructorRepo.findById(instructorId);
        if(opt.isPresent()){
            opt.ifPresent(i -> instructorRepo.delete(i));
            return true;
        }else{
            return false;
        }
    }

    public List<Courses> getMyCourses(Long instructorId){
        return findInstructor(instructorId).getCoursesList();
    }

    public List<Instructors> getAllInstructors(){
        return instructorRepo.findAll();
    }

    public Instructors findInstructor(Long courseId){
        Optional<Instructors> opt = instructorRepo.findById(courseId);
        if(opt.isPresent()){
            return opt.get();
        }else{
            return null;
        }
    }

    public List<Students> getStudentsCourse(Long courseId){
        Courses course = courseRepo.findById(courseId).get();
        List<Students> studentsList = new ArrayList<>();
        studentsList.addAll(course.getStudentsList());
        return studentsList;
    }
}
