package com.example.acme.controllerunittest;

import com.example.acme.conf.SpringSecurityWebAuxTestConfig;
import com.example.acme.entities.Instructors;

import com.example.acme.services.InstructorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(InstructorController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@TestPropertySource(locations = "classpath:application.properties")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstructorControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstructorService instructorService;

    Instructors instructor;

    @BeforeEach
    public void setup(){
        instructor = new Instructors("instName", "instLastName", Date.valueOf("2021-01-01"));
    }

    @Test
    @WithUserDetails("emm")
    @Transactional
    public void addInstructorTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(instructor);
        mockMvc.perform(post("/instructors/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputjson)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("New instructor "+instructor.getInstructorName()+" has been added")));
           }

    @Test
    @WithUserDetails("emm")
    //@Sql("/instructor-data.sql")
    @Order(4)
    public void deleteInstructorTest() throws  Exception{
        mockMvc.perform(delete("/instructors/delete/{instructorId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delete success")));
    }

    @Test
    @WithUserDetails("emm")
    //@Sql("/instructor-data.sql")
    @Order(3)
    public void getAllInstructorsTest() throws  Exception{
        mockMvc.perform(get("/instructors/list")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    @WithUserDetails("emm")
    //@Sql("/instructor-data.sql")
    @Order(2)
    public void getCourses() throws  Exception{
        mockMvc.perform(get("/instructors/mycourses/{instructorId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    @WithUserDetails("emm")
    @Sql("/instructor-data.sql")
    @Order(1)
    public void getStudentsCourses() throws  Exception{
        mockMvc.perform(get("/instructors/students/course/{courseId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}
