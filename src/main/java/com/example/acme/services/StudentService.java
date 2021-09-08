package com.example.acme.services;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Students;
import com.example.acme.repositpories.CourseRepo;
import com.example.acme.repositpories.StudentRepo;
import org.springframework.stereotype.Service;

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

}
