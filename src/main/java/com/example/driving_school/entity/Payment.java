package com.example.driving_school.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
