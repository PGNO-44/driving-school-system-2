package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Instructor;
import com.example.demo.model.Schedule;
import com.example.demo.model.Student;
import com.example.demo.service.BookingService;
import com.example.demo.service.InstructorService;
import com.example.demo.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final ScheduleService scheduleService;
    private final InstructorService instructorService;

    public BookingController(BookingService bookingService,
                             ScheduleService scheduleService,
                             InstructorService instructorService) {
        this.bookingService = bookingService;
        this.scheduleService = scheduleService;
        this.instructorService = instructorService;
    }

    @GetMapping("/new")
    public String showBookingForm(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        model.addAttribute("booking", new Booking());
        return "new-booking";
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute Booking booking,
                                HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        booking.setStudentName(student.getFirstName() + " " + student.getLastName());
        booking.setStatus("PENDING");
        bookingService.saveBooking(booking);

        return "redirect:/student/bookings/my";
    }

    @GetMapping("/my")
    public String viewMyBookings(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        String studentName = student.getFirstName() + " " + student.getLastName();
        List<Booking> bookings = bookingService.getBookingsByStudent(studentName);
        model.addAttribute("bookings", bookings);
        return "my-bookings";
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam String instructorName,
                                @RequestParam String date,
                                @RequestParam String timeSlot,
                                HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        String studentName = student.getFirstName() + " " + student.getLastName();

        // Delete the booking
        bookingService.deleteBooking(studentName, instructorName, date, timeSlot);

        // Update schedule availability
        Schedule updatedSchedule = new Schedule(
                instructorName,
                date,
                timeSlot,
                false
        );
        scheduleService.updateSchedule(updatedSchedule);

        return "redirect:/student/bookings/my";
    }
}