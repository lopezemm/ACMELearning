package com.example.acme.serviceunittest;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Instructors;
import com.example.acme.entities.Students;
import com.example.acme.services.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {
    @Mock
    InstructorService instructorService;

    private Instructors instructor;

    @BeforeEach
    public void setup(){
        instructor = new Instructors("instName", "instLastName", Date.valueOf("2021-01-01"));
    }

    @Test
    public void addInstructorTest() throws Exception {
        Instructors i = new Instructors("instName", "instLastName", Date.valueOf("2021-01-01"));
        i.setId(1L);
        when(instructorService.addInstructor(instructor)).thenReturn(i.getInstructorName());
        instructorService.addInstructor(instructor);
        verify(instructorService, times(1)).addInstructor(instructor);
    }

    @Test
    public void deleteInstructorTest() throws Exception {
        when(instructorService.deleteInstructor(1L)).thenReturn(true);
        instructorService.deleteInstructor(1L);
        verify(instructorService, times(1)).deleteInstructor(1L);
    }

    @Test
    public void getMyCoursesTest() throws Exception {
        Courses c = new Courses();
        c.setCourseName("test");
        c.setIsStarted("N");
        c.setCreateDate(Date.valueOf("2021-01-01"));
        c.setId(1L);
        when(instructorService.getMyCourses(1L)).thenReturn(Arrays.asList(c));
        instructorService.getMyCourses(1L);
        verify(instructorService, times(1)).getMyCourses(1L);
    }

    @Test
    public void getAllInstructorsTest() throws Exception {
        when(instructorService.getAllInstructors()).thenReturn(Arrays.asList(instructor));
        instructorService.getAllInstructors();
        verify(instructorService, times(1)).getAllInstructors();
    }

    @Test
    public void findInstructorTest() throws Exception{
        when(instructorService.findInstructor(1L)).thenReturn(instructor);
        instructorService.findInstructor(1L);
        verify(instructorService, times(1)).findInstructor(1L);

    }

    @Test
    public void getStudentsCourseTest() throws Exception {
        Students s = new Students();
        s.setStudentName("test");
        s.setStudentAge(18);
        s.setStudentLastName("test");
        s.setId(1L);
        when(instructorService.getStudentsCourse(1L)).thenReturn(Arrays.asList(s));
        instructorService.getStudentsCourse(1L);
        verify(instructorService, times(1)).getStudentsCourse(1L);
    }
}
