package com.example.acme.serviceunittest;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Instructors;
import com.example.acme.services.CourseService;
import com.example.acme.services.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoursesServiceUnitTest {

    @Mock
    CourseService courseService;

    private Courses c;

    @BeforeEach
    public void setup(){
        c = new Courses();
        c.setCourseName("test");
        c.setIsStarted("N");
        c.setId(1L);
    }

    @Test
    public void addCourseTest() throws Exception {
        when(courseService.addCourse(c)).thenReturn(c.getCourseName());
        assertEquals("test",courseService.addCourse(c));
        verify(courseService, times(1)).addCourse(c);
    }

    @Test
    public void addCourseOVTest() throws Exception {
        when(courseService.addCourse(c, 1L)).thenReturn(c.getCourseName());
        assertEquals("test",courseService.addCourse(c, 1L));
        verify(courseService, times(1)).addCourse(c, 1L);
    }

    @Test
    public void deleteCourseTest() throws Exception {
        when(courseService.deleteCourse(1L)).thenReturn(true);
        assertEquals(true,courseService.deleteCourse(1L));
        verify(courseService, times(1)).deleteCourse(1L);
    }

    @Test
    public void getAllCoursesTest(){
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(c));
        assertEquals(1, courseService.getAllCourses().size());
        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    public void startCourseTest() throws Exception {
        when(courseService.startCourse(1L)).thenReturn(c.getCourseName());
        assertEquals("test",courseService.startCourse(1L));
        verify(courseService, times(1)).startCourse(1L);
    }

    @Test
    public void cancelCourseTest() throws Exception {
        when(courseService.cancelCourse(1L)).thenReturn(c.getCourseName());
        assertEquals("test",courseService.cancelCourse(1L));
        verify(courseService, times(1)).cancelCourse(1L);
    }

    @Test
    public void findCourseTest() throws Exception {
        when(courseService.findCourse(1L)).thenReturn(c);
        assertEquals(1,courseService.findCourse(1L).getId());
        verify(courseService, times(1)).findCourse(1L);
    }

}
