package com.example.driving_school.serviceImpl;

import com.example.driving_school.entity.Payment;
import com.example.driving_school.repository.PaymentRepository;
import com.example.driving_school.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        try {
            return paymentRepository.save(payment);
        } catch (IOException e) {
            throw new RuntimeException("Error saving payment", e);
        }
    }

    //Existing CRUD Methods

    @Override
    public Payment createPayment(Payment payment) {
        return savePayment(payment);
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        try {
            Optional<Payment> payment = paymentRepository.findById(paymentId);
            return payment.orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
        } catch (IOException e) {
            throw new RuntimeException("Error retrieving payment", e);
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        try {
            return paymentRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException("Error retrieving payments", e);
        }
    }

    @Override
    public Payment updatePayment(String paymentId, Payment payment) {
        try {
            payment.setPaymentId(paymentId);
            return paymentRepository.save(payment);
        } catch (IOException e) {
            throw new RuntimeException("Error updating payment", e);
        }
    }

    @Override
    public void deletePayment(String paymentId) {
        try {
            paymentRepository.delete(paymentId);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting payment", e);
        }
    }
}
