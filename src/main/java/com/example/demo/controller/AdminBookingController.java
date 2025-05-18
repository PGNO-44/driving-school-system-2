package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/bookings")
public class AdminBookingController {

    private final BookingService bookingService;

    public AdminBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/pending")
    public String showPendingBookings(Model model) {
        model.addAttribute("pendingBookings", bookingService.getPendingBookings());
        return "pending-bookings";
    }

    @PostMapping("/approve/{bookingId}")
    public String approveBooking(@PathVariable int bookingId,
                                 @RequestParam(required = false) String comments) {
        bookingService.updateBookingStatus(bookingId, "APPROVED", comments);
        return "redirect:/admin/bookings/pending";
    }

    @PostMapping("/reject/{bookingId}")
    public String rejectBooking(@PathVariable int bookingId,
                                @RequestParam(required = false) String comments) {
        bookingService.updateBookingStatus(bookingId, "REJECTED", comments);
        return "redirect:/admin/bookings/pending";
    }
}