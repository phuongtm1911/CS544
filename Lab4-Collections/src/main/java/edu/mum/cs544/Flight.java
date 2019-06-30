package edu.mum.cs544;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    @OrderColumn(name = "sequence")
    private List<Passenger> passengers = new ArrayList<>();

    public Flight() {
    }

    public Flight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public boolean addPassenger(Passenger passenger) {
        if (passengers.add(passenger)) {
            return true;
        }
        return false;
    }

    public boolean removePassenger(Passenger passenger) {
        if (passengers.remove(passenger)) {
            return true;
        }
        return false;
    }
}
