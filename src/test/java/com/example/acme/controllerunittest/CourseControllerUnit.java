package com.example.acme.controllerunittest;


import com.example.acme.controllers.CourseController;
import com.example.acme.entities.Courses;
import com.example.acme.services.CourseService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@WebMvcTest(CourseController.class)
@Import(CourseController.class)
public class CourseControllerUnit {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CourseService courseService;

    private Courses course;

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
        course = new Courses();
        course.setCourseName("test");
        course.setIsStarted("N");
        course.setCreateDate(Date.valueOf("2021-02-01"));
        course.setId(1L);
    }

    @Test
    public void addCourseTest() throws Exception{
        when(courseService.addCourse(course,1L)).thenReturn("test");
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(course);
        mockMvc.perform(post("/courses/add/{instructorId}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(inputjson).accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCourseSuccessTest() throws Exception {
        when(courseService.deleteCourse(1L)).thenReturn(true);
        mockMvc.perform(delete("/courses/delete/{courseId}", 1L).contentType(MediaType.APPLICATION_JSON)
                        ).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("Delete success")));;
    }

    @Test
    public void deleteCourseNotFoundTest() throws Exception {
        when(courseService.deleteCourse(1L)).thenReturn(false);
        mockMvc.perform(delete("/courses/delete/{courseId}", 1L).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isNotFound()).andExpect(content().string(containsString("Course with Id " + course.getId() + " was not found")));;
    }

    @Test
    public void getAllCoursesTest() throws Exception {
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(course));
        mockMvc.perform(get("/courses/list").contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].courseName", is("test")))
                .andExpect(jsonPath("$[0].isStarted", is("N")))
                .andExpect(jsonPath("$[0].createDate", is("2021-02-01")));
    }

    @Test
    public void starCourseTest() throws Exception{
        when(courseService.startCourse(1L)).thenReturn(course.getCourseName());
        mockMvc.perform(put("/courses/start/{courseId}", 1L).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("Course " + course.getCourseName()+ " has been set as started")));;

    }

    @Test
    public void cancelCourseTest() throws Exception{
        when(courseService.cancelCourse(1L)).thenReturn(course.getCourseName() + " has been canceled ");
        mockMvc.perform(put("/courses/cancel/{courseId}", 1L).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("Course " + course.getCourseName()+ " has been canceled ")));;

    }
 }
