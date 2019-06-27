package edu.mum.cs544;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany
    @MapKey(name = "studentId")
    private Map<Integer, Student> students = new HashMap<>();

    public School() {
    }

    public School(String name, Map<Integer, Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<Integer, Student> students) {
        this.students = students;
    }
}
