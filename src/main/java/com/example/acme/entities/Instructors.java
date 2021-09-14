package com.example.acme.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Instructors implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String instructorName;
    private String instructorLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate instructorJoinDate;
    @OneToMany(mappedBy = "instructor",  orphanRemoval=true)
    private List<Courses> coursesList = new ArrayList<>();

    public Instructors() {
    }

    public Instructors(String instructorName, String instructorLastName, LocalDate instructorJoinDate) {
        this.instructorName = instructorName;
        this.instructorLastName = instructorLastName;
        this.instructorJoinDate = instructorJoinDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorLastName() {
        return instructorLastName;
    }

    public void setInstructorLastName(String instructorLastName) {
        this.instructorLastName = instructorLastName;
    }

    public LocalDate getInstructorJoinDate() {
        return instructorJoinDate;
    }

    public void setInstructorJoinDate(LocalDate instructorJoinDate) {
        this.instructorJoinDate = instructorJoinDate;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }
    @JsonManagedReference
    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public String toString() {
        return "Instructors{" +
                "id=" + id +
                ", instructorName='" + instructorName + '\'' +
                ", instructorLastName='" + instructorLastName + '\'' +
                ", instructorJoinDate=" + instructorJoinDate +
                '}';
    }
}
