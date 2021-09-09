package com.example.acme.serviceunittest;

import com.example.acme.entities.Courses;
import com.example.acme.entities.Instructors;
import com.example.acme.entities.Students;
import com.example.acme.services.InstructorService;
import com.example.acme.services.StudentService;
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
public class StudentsServiceUnitTest {

    @Mock
    StudentService studentService;

    private Students student;

    private  Courses c;

    @BeforeEach
    public void setup(){
        student = new Students("studentname", "studentLastName", 18);
        c = new Courses();
        c.setCourseName("test");
        c.setIsStarted("N");
        c.setId(1L);
    }

    @Test
    public void addStudentTest() throws Exception {
        when(studentService.addStudent(student)).thenReturn(student.getStudentName());
        assertEquals("studentname", studentService.addStudent(student));
        verify(studentService, times(1)).addStudent(student);
    }

    @Test
    public void deleteStudentTest() throws Exception {
        when(studentService.deleteStudent(1L)).thenReturn(true);
        assertEquals(true,studentService.deleteStudent(1L));
        verify(studentService, times(1)).deleteStudent(1L);
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student));
        assertEquals(1,studentService.getAllStudents().size());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void getAllCoursesTest() throws Exception {
        when(studentService.getAllCourses()).thenReturn(Arrays.asList(c));
        assertEquals(1,studentService.getAllCourses().size());
        verify(studentService, times(1)).getAllCourses();
    }

    @Test
    public void enrollCourseTest() throws Exception {
        when(studentService.enrollCourse(1L,1L)).thenReturn("Student " + student.getStudentName() + " has been enrolled to " + c.getCourseName() + " course");
        assertEquals("Student studentname has been enrolled to test course",studentService.enrollCourse(1L,1L));
        verify(studentService, times(1)).enrollCourse(1L, 1L );
    }

    @Test
    public void dropCourseTest() throws Exception {
        when(studentService.dropCourse(1L,1L)).thenReturn("Student " + student.getStudentName() + " has dropped " + c.getCourseName() + " course");
        assertEquals( "Student studentname has dropped test course",studentService.dropCourse(1L, 1L));
        verify(studentService, times(1)).dropCourse(1L,1L);
    }

    @Test
    public void getEnrolledCoursesTest() throws Exception{
        when(studentService.getEnrolledCourses(1L)).thenReturn(Arrays.asList(c));
        assertEquals(1,studentService.getEnrolledCourses(1L).size());
        verify(studentService, times(1)).getEnrolledCourses(1L);
    }
}
