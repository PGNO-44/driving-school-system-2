package com.example.driving_school.repository;

import com.example.driving_school.entity.Payment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRepository.class);

    @Value("${app.data.file}")
    private String dataFile;

    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;

    public PaymentRepository(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
    }

    public Payment save(Payment payment) throws IOException {
        List<Payment> payments = findAll();
        if (payment.getPaymentId() == null || payment.getPaymentId().isEmpty()) {
            String newId = "PAY" + String.format("%03d", payments.size() + 1);
            payment.setPaymentId(newId);
            payments.add(payment);
        } else {
            Optional<Payment> existing = payments.stream()
                    .filter(p -> p.getPaymentId().equals(payment.getPaymentId()))
                    .findFirst();
            if (existing.isPresent()) {
                payments.remove(existing.get());
                payments.add(payment);
            } else {
                throw new RuntimeException("Payment not found with id: " + payment.getPaymentId());
            }
        }
        writeToFile(payments);
        return payment;
    }

    public Optional<Payment> findById(String paymentId) throws IOException {
        return findAll().stream()
                .filter(payment -> payment.getPaymentId().equals(paymentId))
                .findFirst();
    }

    public List<Payment> findAll() throws IOException {
        Resource resource = resourceLoader.getResource(dataFile);
        File file = resource.getFile();

        if (!file.exists()) {
            logger.info("Payments file not found at {}, creating new file.", file.getAbsolutePath());
            file.getParentFile().mkdirs();
            Files.createFile(file.toPath());
            objectMapper.writeValue(file, new ArrayList<>());
        }

        return objectMapper.readValue(file, new TypeReference<List<Payment>>() {});
    }

    public void delete(String paymentId) throws IOException {
        List<Payment> payments = findAll();
        boolean removed = payments.removeIf(payment -> payment.getPaymentId().equals(paymentId));
        if (!removed) {
            throw new RuntimeException("Payment not found with id: " + paymentId);
        }
        writeToFile(payments);
    }

    private void writeToFile(List<Payment> payments) throws IOException {
        Resource resource = resourceLoader.getResource(dataFile);
        File file = resource.getFile();

        if (!file.exists()) {
            logger.info("Payments file not found at {}, creating new file.", file.getAbsolutePath());
            file.getParentFile().mkdirs();
            Files.createFile(file.toPath());
        }

        if (!file.canWrite()) {
            throw new IOException("File is not writable: " + file.getAbsolutePath());
        }

        logger.info("Writing payments to: {}", file.getAbsolutePath());
        objectMapper.writeValue(file, payments);
    }
}