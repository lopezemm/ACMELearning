package com.example.acme.entities;

import com.example.acme.util.CourseStartedValidation;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseName;
    private String isStarted;
    private Date createDate;

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

    public Courses(String courseName, String isStarted, Date createDate) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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


