package com.example.driving_school.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Payment {

    @JsonProperty("paymentId")
    private String paymentId;

    @JsonProperty("studentId")
    private String studentId;

    @JsonProperty("lessonId")
    private String lessonId;

    @JsonProperty("paymentDate")
    private String paymentDate;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("paymentStatus")
    private String paymentStatus;

    public Payment(){}

    public Payment(String paymentId, String studentId, String lessonId, String paymentDate, double amount, String paymentStatus) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getLessonId() {
        return lessonId;
    }
    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


}
