package com.example.driving_school.service;

import com.example.driving_school.entity.Payment;
import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment getPaymentById(String paymentId);
    List<Payment> getAllPayments();
    Payment updatePayment(String paymentId, Payment payment);
    void deletePayment(String paymentId);
}
