package com.example.acme.services;

import com.example.acme.entities.Instructors;
import com.example.acme.repositpories.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    InstructorRepo instructorRepo;

    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
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

    public List<Instructors> getAllInstructors(){
        return instructorRepo.findAll();
    }
}
