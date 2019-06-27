package edu.mum.cs544;

import javax.persistence.*;

@Entity
public class Laptop {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    private Employee employee;

    public Laptop() {
    }

    public Laptop(String name, Employee employee) {
        this.name = name;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
