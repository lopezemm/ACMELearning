package com.example.acme.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Students implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String studentName;
    private String studentLastName;
    private int studentAge;
    @ManyToMany(mappedBy = "studentsList")
    private List<Courses> coursesList = new ArrayList<>();

    public Students() {
    }

    public Students(String studentName, String studentLastName, int studentAge) {
        this.studentName = studentName;
        this.studentLastName = studentLastName;
        this.studentAge = studentAge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", studentAge=" + studentAge +
                '}';
    }
}
