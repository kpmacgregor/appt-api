package com.kpmacgregor.apptapi.domains.participant;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpmacgregor.apptapi.domains.appointment.Appointment;
import com.kpmacgregor.apptapi.domains.entity.BaseEntity;

@Entity
public class Participant extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "participants")
    private List<Appointment> appointments;

    public Participant() {
    }

    public Participant(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Participant [id=" + this.getId() + ", email=" + email + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }

}
