package edu.mum.cs544;

import javax.persistence.*;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private String appdate;
    @Embedded
    private Payment payment;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    private Patient patient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(String appdate, Payment payment, Patient patient, Doctor doctor) {
        this.appdate = appdate;
        this.payment = payment;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
