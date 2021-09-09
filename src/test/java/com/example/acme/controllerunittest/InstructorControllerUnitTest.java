package com.example.acme.controllerunittest;

import com.example.acme.controllers.InstructorController;
import com.example.acme.entities.Courses;
import com.example.acme.entities.Instructors;
import com.example.acme.entities.Students;
import com.example.acme.services.InstructorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InstructorController.class)
@Import(InstructorController.class)
public class InstructorControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InstructorService instructorService;

    private Instructors instructor;

    @Configuration
    @EnableWebSecurity
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http
                    .csrf().disable()
                    .authorizeRequests().anyRequest().anonymous();
        }
    }

    @BeforeEach
    public void setup(){
        instructor = new Instructors();
        instructor.setInstructorName("test");
        instructor.setId(1L);
        instructor.setInstructorLastName("lastname");
        instructor.setInstructorJoinDate(Date.valueOf("2021-01-01"));
    }

    @Test
    public void addInstructorTest() throws Exception{
        when(instructorService.addInstructor(instructor)).thenReturn(instructor.getInstructorName());
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(instructor);
        mockMvc.perform(post("/instructors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputjson)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteInstructorTest() throws  Exception{
        when(instructorService.deleteInstructor(1L)).thenReturn(true);
        mockMvc.perform(delete("/instructors/delete/{instructorId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delete success")));
    }

    @Test
    public void getAllInstructorsTest() throws  Exception{
        when(instructorService.getAllInstructors()).thenReturn(Arrays.asList(instructor));
        mockMvc.perform(get("/instructors/list")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].instructorName", is("test")))
                .andExpect(jsonPath("$[0].instructorLastName", is("lastname")))
                .andExpect(jsonPath("$[0].instructorJoinDate", is("2021-01-01")))
                .andExpect(jsonPath("$[0].coursesList", is(isA(net.minidev.json.JSONArray.class))));

    }

    @Test
    public void getCourses() throws  Exception{
        Courses c = new Courses();
        c.setCourseName("test");
        c.setIsStarted("N");
        c.setCreateDate(Date.valueOf("2021-01-01"));
        c.setId(1L);
        when(instructorService.getMyCourses(1L)).thenReturn(Arrays.asList(c));
        mockMvc.perform(get("/instructors/mycourses/{instructorId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].courseName", is("test")))
                .andExpect(jsonPath("$[0].isStarted", is("N")))
                .andExpect(jsonPath("$[0].createDate", is("2021-01-01")));

    }

    @Test
    public void getStudentsCourses() throws  Exception{
        Students s = new Students();
        s.setStudentName("test");
        s.setStudentAge(18);
        s.setStudentLastName("lastname");
        s.setId(1L);
        when(instructorService.getStudentsCourse(1L)).thenReturn(Arrays.asList(s));
        mockMvc.perform(get("/instructors/students/course/{courseId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].studentName", is("test")))
                .andExpect(jsonPath("$[0].studentLastName", is("lastname")))
                .andExpect(jsonPath("$[0].studentAge", is(18)));
    }
}
