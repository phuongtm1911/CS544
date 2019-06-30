package edu.mum.cs544;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean addOrder(Order order) {
        if (orders.add(order)) {
            order.setCustomer(this);
            return true;
        }
        return false;
    }

    public boolean removeOrder(Order order) {
        if (orders.remove(order)) {
            order.setCustomer(null);
            return true;
        }
        return false;
    }
}
