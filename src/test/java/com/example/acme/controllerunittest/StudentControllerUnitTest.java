package com.example.acme.controllerunittest;

import com.example.acme.controllers.CourseController;
import com.example.acme.controllers.StudentController;
import com.example.acme.entities.Courses;
import com.example.acme.entities.Students;
import com.example.acme.services.CourseService;
import com.example.acme.services.StudentService;
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
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@Import(StudentController.class)
public class StudentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    private Students student;
    private Courses c;

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
        student = new Students();
        student.setStudentAge(18);
        student.setStudentName("test");
        student.setStudentLastName("lastname");
        student.setId(1L);
        c = new Courses();
        c.setCourseName("test");
        c.setIsStarted("N");
        c.setId(1L);
        c.setCreateDate(LocalDate.parse("2021-01-01"));
    }

    @Test
    public void addStudentTest() throws Exception{
        when(studentService.addStudent(student)).thenReturn(student.getStudentName());
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputjson)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentTest() throws  Exception{
        when(studentService.deleteStudent(1L)).thenReturn(true);
        mockMvc.perform(delete("/students/delete/{studentId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delete success")));
    }

    @Test
    public void getAllStudentsTest() throws  Exception{
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student));
        mockMvc.perform(get("/students/list")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].studentName", is("test")))
                .andExpect(jsonPath("$[0].studentLastName", is("lastname")))
                .andExpect(jsonPath("$[0].studentAge", is(18)));
    }

    @Test
    public void getAllCoursesTest() throws  Exception{
        when(studentService.getAllCourses()).thenReturn(Arrays.asList(c));
        mockMvc.perform(get("/students/allcourses")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].courseName", is("test")))
                .andExpect(jsonPath("$[0].isStarted", is("N")))
                .andExpect(jsonPath("$[0].createDate", is("2021-01-01")));
    }

    @Test
    public void enrollToACourseTest() throws  Exception{
        when(studentService.enrollCourse(1L,1L)).thenReturn("Student " + student.getStudentName() + " has been enrolled to " + c.getCourseName() + " course");
        mockMvc.perform(post("/students/enroll/{studentId}/{courseId}", 1,1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Student test has been enrolled to test course")));
    }

    @Test
    public void dropToACourseTest() throws  Exception{
        when(studentService.dropCourse(1L,1L)).thenReturn("Student " + student.getStudentName() + " has dropped " + c.getCourseName() + " course");
        mockMvc.perform(post("/students/drop/{studentId}/{courseId}", 1,1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Student test has dropped test course")));
    }

    @Test
    public void getEnrolledCoursesTest() throws Exception {
        when(studentService.getEnrolledCourses(1L)).thenReturn(Arrays.asList(c));
        mockMvc.perform(get("/students/mycourses/{studentId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].courseName", is("test")))
                .andExpect(jsonPath("$[0].isStarted", is("N")))
                .andExpect(jsonPath("$[0].createDate", is("2021-01-01")));


    }
}
