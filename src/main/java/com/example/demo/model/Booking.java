package com.example.demo.model;

public class Booking {

    private int bookingId;
    private String studentName;
    private String instructorName;
    private String date;
    private String timeSlot;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    private String adminComments;

    public Booking() {
        this.status = "PENDING";
    }

    public Booking(String studentName, String instructorName, String date, String timeSlot) {
        this.studentName = studentName;
        this.instructorName = instructorName;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminComments() {
        return adminComments;
    }

    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

}
