package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public class AdminService {

    private final BookingService bookingService = new BookingService();

    // Bookings
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    public void deleteBooking(String student, String instructor, String date, String timeSlot) {
        bookingService.deleteBooking(student, instructor, date, timeSlot);
    }

}
