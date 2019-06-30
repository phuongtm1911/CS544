package edu.mum.cs544;

import org.hibernate.id.uuid.CustomVersionOneStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer orderid;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order() {
    }

    public Order(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public boolean addOrderLine(OrderLine orderLine) {
        if (orderLines.add(orderLine)) {
            return true;
        }
        return false;
    }

    public boolean removeOrderLine(OrderLine orderLine) {
        if (orderLines.remove(orderLine)) {
            return true;
        }
        return false;
    }
}
