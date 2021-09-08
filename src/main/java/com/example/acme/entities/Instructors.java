package com.example.acme.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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
    private Date instructorJoinDate;
    @OneToMany(mappedBy = "instructor")
    private List<Courses> coursesList = new ArrayList<>();

    public Instructors(String instructorName, String instructorLastName, Date instructorJoinDate) {
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

    public Date getInstructorJoinDate() {
        return instructorJoinDate;
    }

    public void setInstructorJoinDate(Date instructorJoinDate) {
        this.instructorJoinDate = instructorJoinDate;
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
