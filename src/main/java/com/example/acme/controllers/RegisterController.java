package com.example.acme.controllers;

import com.example.acme.entities.AcmeUsers;
import com.example.acme.services.AcmeUserService;
import com.example.acme.util.UtilValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    AcmeUserService acmeUserService;

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody AcmeUsers acmeUsers){
        try{
            if(!UtilValidations.validateRole(acmeUsers.getRole())) return new ResponseEntity<String>("Roles must be INSTR or STD", HttpStatus.BAD_REQUEST);
            acmeUserService.suscribe(acmeUsers);
            return new ResponseEntity<String>("Registration success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("App Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
