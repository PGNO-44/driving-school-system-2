package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User {

    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Student(String email, String password, String role, String firstName, String lastName, String phone, String address) {
        super(email, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public Student() {

    }
}
