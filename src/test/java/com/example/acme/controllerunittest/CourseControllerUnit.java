package com.example.acme.controllerunittest;


import com.example.acme.controllers.CourseController;
import com.example.acme.entities.Courses;
import com.example.acme.services.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CourseControllerUnit {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CourseService courseService;

    Courses course;

    @BeforeEach
    public void setup(){
        course = new Courses();
        course.setCourseName("test");
        course.setIsStarted("N");
    }

    @Test
    public void addCourseTest() throws Exception{
        when(courseService.addCourse(course)).thenReturn(course.getCourseName());
        courseService.addCourse(course);
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(course);
        mockMvc.perform(post("/courses/add/{instructorId}").contentType(MediaType.APPLICATION_JSON)
                .content(inputjson)).andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().string(containsString("New instructor "+instructor.getInstructorName()+" has been added")));
    }
}
