package edu.mum.cs544;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean addReservation(Reservation reservation) {
        if (reservations.add(reservation)) {
            return true;
        }
        return false;
    }

    public boolean removeReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            return true;
        }
        return false;
    }
}
