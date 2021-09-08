package com.example.acme.controllers;

import com.example.acme.entities.Instructors;
import com.example.acme.entities.Students;
import com.example.acme.services.InstructorService;
import com.example.acme.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addStudent(@RequestBody Students student){
        try {
            String studentName = studentService.addStudent(student);
            return new ResponseEntity<String>("New student " + studentName + " has been added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/delete/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?>delStudent(@PathVariable long studentId){
        try {
            if(studentService.deleteStudent(studentId))
                return new ResponseEntity<String>("Delete success", HttpStatus.OK);
            return new ResponseEntity<String>("Student with Id " + studentId + " was not found", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?>getStudents(){
        try{
            return new ResponseEntity<List<Students>>(studentService.getAllStudents(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
