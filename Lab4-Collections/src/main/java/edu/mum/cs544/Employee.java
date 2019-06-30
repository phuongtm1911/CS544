package edu.mum.cs544;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Laptop> laptops = new HashSet<>();

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
        laptop.setEmployee(this);
    }

    public boolean removeLaptop(Laptop laptop) {
        if (laptops.remove(laptop)) {
            laptop.setEmployee(null);
            return true;
        }
        return false;

    }
}
