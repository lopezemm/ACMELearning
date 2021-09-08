package com.example.acme.services;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Students;
import com.example.acme.repositpories.CourseRepo;
import com.example.acme.repositpories.StudentRepo;
import com.example.acme.util.UtilValidations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StudentService {

    StudentRepo studentRepo;

    CourseRepo courseRepo;

    public StudentService(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public String addStudent(Students student) {
        studentRepo.save(student);
        return studentRepo.findByStudentName(student.getStudentName()).getStudentName();

    }

    public boolean deleteStudent(long studentId) {
        Optional<Students> opt = studentRepo.findById(studentId);
        if(opt.isPresent()){
            opt.ifPresent(s -> studentRepo.delete(s));
            return true;
        }else{
            return false;
        }
    }

    public List<Students> getAllStudents() {
        return studentRepo.findAll();
    }

    public List<Courses> getAllCourses(){
        return courseRepo.findAll();
    }

    public String enrollCourse(Long studentId, Long courseId){
        Courses course = courseRepo.findById(courseId).get();
        if(UtilValidations.isStarted(course.getIsStarted()))
            return "The course " + course.getCourseName() + " has already started, you cannot enroll to this course";
        Students student = studentRepo.findById(studentId).get();
        student.getCoursesList().add(course);
        course.getStudentsList().add(student);
        courseRepo.save(course);
        return "Student " + student.getStudentName() + " has been enrolled to " + course.getCourseName() + " course";
    }

}
