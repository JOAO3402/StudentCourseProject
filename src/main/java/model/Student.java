package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String name;
    private Date birthDate;
    private Double grade;
    private Course course;
    private Integer id;

    public Student() {
    }

    public Student(Integer id, String name, Date birthDate, Course course, Double grade) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.course = course;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getGrade() {
        return grade;
    }
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "[Name = " + name + ", Birthdate = " + sdf.format(birthDate) + ", Grade = " + grade + ", Course = " + course + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return Objects.equals(name, student.name) && Objects.equals(birthDate, student.birthDate) && Objects.equals(grade, student.grade) && Objects.equals(course, student.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, grade, course);
    }
}
