package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService = new AdminService();

    // --------------------- Dashboard ---------------------
    @GetMapping({"","/dashboard"})
    public String showDashboard() {
        return "admin-dashboard";
    }

    // --------------------- Bookings ---------------------
    @GetMapping("/bookings")
    public String showAllBookings(Model model) {
        List<Booking> bookings = adminService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "admin-bookings";
    }

    @GetMapping("/bookings/delete")
    public String deleteBooking(@RequestParam String studentName,
                                @RequestParam String instructorName,
                                @RequestParam String date,
                                @RequestParam String timeSlot) {
        adminService.deleteBooking(studentName, instructorName, date, timeSlot);
        return "redirect:/admin/bookings";
    }
}
