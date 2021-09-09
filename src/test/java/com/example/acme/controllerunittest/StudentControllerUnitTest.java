package com.example.acme.controllerunittest;

import com.example.acme.conf.SpringSecurityWebAuxTestConfig;
import com.example.acme.entities.Instructors;
import com.example.acme.entities.Students;
import com.example.acme.services.InstructorService;
import com.example.acme.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    Students student;

    @BeforeEach
    public void setup(){
        student = new Students("studentName", "instLastName", 18);
    }

    @Test
    @WithUserDetails("emm")
    @Transactional
    public void addStudentTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String inputjson =  objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputjson)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("New student "+student.getStudentName()+" has been added")));
    }

    @Test
    @WithUserDetails("emm")
    @Order(5)
    public void deleteStudentTest() throws  Exception{
        mockMvc.perform(delete("/students/delete/{studentId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Delete success")));
    }

    @Test
    @WithUserDetails("emm")
    @Order(2)
    public void getAllStudentsTest() throws  Exception{
        mockMvc.perform(get("/students/list")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    @WithUserDetails("emm")
    @Order(1)
    @Sql("/instructor-data.sql")
    public void getAllCoursesTest() throws  Exception{
        mockMvc.perform(get("/students/allcourses")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }
    @Test
    @WithUserDetails("emm")
    @Order(4)
    @Transactional
    public void enrollToACourseTest() throws  Exception{
        mockMvc.perform(post("/students/enroll/{studentId}/{courseId}", 1,1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Student test has been enrolled to java course")));
    }

    @Test
    @WithUserDetails("emm")
    @Order(3)
    @Transactional
    public void dropToACourseTest() throws  Exception{
        mockMvc.perform(post("/students/drop/{studentId}/{courseId}", 1,1)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Student test has dropped java course")));
    }

}
