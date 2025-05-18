package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User{

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private double experience;

    public Instructor(String email, String password, String role, String firstName, String lastName, String address, String phoneNumber, double experience) {
        super(email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public Instructor() {}
}
