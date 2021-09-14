package com.example.acme.entities;

import com.example.acme.util.CourseStartedValidation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Entity
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseName;
    private String isStarted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "instructorId")
    private Instructors instructor;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    private List<Students> studentsList = new ArrayList<>();

    public Courses() {
    }

    public Courses(String courseName, String isStarted, LocalDate createDate) {
        this.courseName = courseName;
        this.isStarted = isStarted;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIsStarted() {
        return isStarted;
    }

    public void setIsStarted(String isStarted) {
        //check if value is S, C or N, if is not set or is a different value default value is N
        if (CourseStartedValidation.contains(isStarted)) {
            this.isStarted = isStarted;
        }else {
            this.isStarted = "N";
        }
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Instructors getInstructor() {
        return instructor;
    }
    @JsonBackReference
    public void setInstructor(Instructors instructor) {
        this.instructor = instructor;
    }

    public List<Students> getStudentsList() {
        return studentsList;
    }
    @JsonBackReference(value = "students")
    public void setStudentsList(List<Students> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", isStarted=" + isStarted +
                ", createDate=" + createDate +
                '}';
    }
}


