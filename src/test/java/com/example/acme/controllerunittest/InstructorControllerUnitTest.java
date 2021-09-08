package com.example.acme.controllerunittest;

import com.example.acme.controllers.InstructorController;
import com.example.acme.entities.AcmeUsers;
import com.example.acme.entities.Instructors;
import com.example.acme.services.AcmeUserService;
import com.example.acme.services.InstructorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstructorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class InstructorControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstructorService instructorService;

    Instructors instructor;

    @BeforeEach
    public void setup(){
        instructor = new Instructors("instName", "instLastName", Date.valueOf("2021-01-01"));


    }

    @Test
    @WithMockUser(username = "aUser", roles = { "INSTR" })
    public void addInstructorTest() throws Exception{
        doNothing().when(instructorService).addInstructor(isA(Instructors.class));
        instructorService.addInstructor(instructor);
        verify(instructorService, times(1)).addInstructor(instructor);
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(instructor);
        mockMvc.perform(post("/instructors/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputjson)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("New instructor "+instructor.getInstructorName()+" has been added")));
    }

}
