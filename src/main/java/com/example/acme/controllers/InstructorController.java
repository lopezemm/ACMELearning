package com.example.acme.controllers;

import com.example.acme.entities.Instructors;
import com.example.acme.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/instructors")
public class InstructorController {

    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?>addInstructor(@RequestBody Instructors instructor){
        try {
            String instructorName = instructorService.addInstructor(instructor);
            return new ResponseEntity<String>("New instructor " + instructorName + " has been added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/delete/{instructorId}", method = RequestMethod.DELETE)
    public ResponseEntity<?>delInstructor(@PathVariable long instructorId){
        try {
            if(instructorService.deleteInstructor(instructorId))
                return new ResponseEntity<String>("Delete success", HttpStatus.OK);
            return new ResponseEntity<String>("Chef with Id " + instructorId + " was not found", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?>getInstructor(){
        try{
            return new ResponseEntity<List<Instructors>>(instructorService.getAllInstructors(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("An error has been occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
