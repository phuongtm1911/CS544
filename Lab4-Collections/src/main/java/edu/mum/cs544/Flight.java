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
    @OneToMany
    @JoinColumn(name = "passenger_id")
    @OrderColumn(name = "sequence")
    private List<Passenger> passengers = new ArrayList<>();

    public Flight() {
    }

    public Flight(String name, List<Passenger> passengers) {
        this.name = name;
        this.passengers = passengers;
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

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
