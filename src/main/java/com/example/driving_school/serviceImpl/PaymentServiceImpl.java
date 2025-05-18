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

    //In-memory Queue to hold payments temporarily
    private Queue<Payment> paymentQueue = new LinkedList<>();

    //Add a payment to the queue
    public void queuePayment(Payment payment) {
        paymentQueue.add(payment);
        System.out.println("Payment added to queue: " + payment.getPaymentId());
    }

    //Process and save all queued payments
    public void processQueuedPayments() {
        while (!paymentQueue.isEmpty()) {
            Payment payment = paymentQueue.poll(); // Remove first
            savePayment(payment); // Save to file
            System.out.println("Processed and saved: " + payment.getPaymentId());
        }
    }

    //View all payments in the queue (not yet saved)
    public List<Payment> getQueuedPayments() {
        return new ArrayList<>(paymentQueue);
    }

    //Use this for queue processing — not directly exposed via interface
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
        return savePayment(payment); // Direct save without queue
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
