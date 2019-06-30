package edu.mum.cs544;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Office {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Office() {
    }

    public Office(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        if (employees.add(employee)) {
            employee.setOffice(this);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(Employee employee) {
        if (employees.remove(employee)) {
            employee.setOffice(null);
            return true;
        }
        return false;
    }
}
