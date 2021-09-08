package com.example.acme.controllers;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Students;
import com.example.acme.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCourse(@RequestBody Courses course){
        try {
            String courseName = courseService.addCourse(course);
            return new ResponseEntity<String>("New course " + courseName + " has been added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value = "/delete/{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?>delCourse(@PathVariable long courseId){
        try {
            if(courseService.deleteCourse(courseId))
                return new ResponseEntity<String>("Delete success", HttpStatus.OK);
            return new ResponseEntity<String>("Course with Id " + courseId + " was not found", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?>getCourses(){
        try{
            return new ResponseEntity<List<Courses>>(courseService.getAllCourses(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
