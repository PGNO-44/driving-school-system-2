package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Booking {

    private int bookingId;
    private String studentName;
    private String instructorName;
    private String date;
    private String timeSlot;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    private String adminComments;
    private LocalDateTime requestTime;

    public Booking() {
        this.status = "PENDING";
    }

    public Booking(String studentName, String instructorName, String date, String timeSlot) {
        this.studentName = studentName;
        this.instructorName = instructorName;
        this.date = date;
        this.timeSlot = timeSlot;
    }

}
