package com.example.driving_school.controller;

import com.example.driving_school.entity.Payment;
import com.example.driving_school.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//spring said this is web Controller
@Controller
public class PaymentController {

    //paymentService class inject controller class
    @Autowired
    private PaymentService paymentService;

    // Redirect first URL to createPayment page
    @GetMapping("/")
    public String redirectToPayments()   {
        return "redirect:/api/payments/new";
    }

    // Show all payments
    @GetMapping("/api/payments")
    public String getAllPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "listPayment";
    }

    @GetMapping("/api/payments/new")
    public String showCreatePaymentForm(Model model) {
        return "createPayment";
    }

    @PostMapping("/api/payments/new")
    public String createPayment(@ModelAttribute Payment payment, RedirectAttributes redirectAttributes) {
        try {
            Payment savedPayment = paymentService.createPayment(payment);
            redirectAttributes.addFlashAttribute("message", "Payment recorded successfully! ID: " + savedPayment.getPaymentId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving payment: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/api/payments";
    }

    @GetMapping("/api/payments/update/{paymentId}")
    public String showUpdatePaymentForm(@PathVariable String paymentId, Model model) {
        Payment payment = paymentService.getPaymentById(paymentId);
        model.addAttribute("payment", payment);
        return "updatePayment";
    }

    @PostMapping("/api/payments/update/{paymentId}")
    public String updatePayment(@PathVariable String paymentId, @ModelAttribute Payment payment, RedirectAttributes redirectAttributes) {
        try {
            payment.setPaymentId(paymentId);
            paymentService.updatePayment(paymentId, payment);
            redirectAttributes.addFlashAttribute("message", "Payment updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating payment: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/api/payments";
    }

    @GetMapping("/api/payments/delete/{paymentId}")
    public String deletePayment(@PathVariable String paymentId, RedirectAttributes redirectAttributes) {
        try {
            paymentService.deletePayment(paymentId);
            redirectAttributes.addFlashAttribute("message", "Payment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting payment: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/api/payments";
    }
}