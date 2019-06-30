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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    @MapKeyColumn(name = "studentId")
    private Map<Integer, Student> students = new HashMap<>();

    public School() {
    }

    public School(String name) {
        this.name = name;
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

    public boolean addStudent(Student student) {
        if (students.put(student.getId(), student) == null) {
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if (students.remove(student.getId(), student)) {
            return true;
        }
        return false;
    }
}
