package com.example.demo.service;

import com.example.demo.model.Booking;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final String FILE_PATH = "data/bookings.txt";

    public BookingService() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Load all bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        int idCounter = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 5) {
                    Booking booking = new Booking();
                    booking.setBookingId(idCounter++);
                    booking.setStudentName(parts[0]);
                    booking.setInstructorName(parts[1]);
                    booking.setDate(parts[2]);
                    booking.setTimeSlot(parts[3]);
                    booking.setStatus(parts[4]);
                    if (parts.length > 5) {
                        booking.setAdminComments(parts[5]);
                    }
                    bookings.add(booking);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Save a new booking
    public void saveBooking(Booking booking) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.join(",",
                    booking.getStudentName(),
                    booking.getInstructorName(),
                    booking.getDate(),
                    booking.getTimeSlot(),
                    booking.getStatus(),
                    booking.getAdminComments() != null ? booking.getAdminComments() : "");
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Update booking status
    public void updateBookingStatus(int bookingId, String status, String comments) {
        List<Booking> bookings = getAllBookings();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Booking booking : bookings) {
                if (booking.getBookingId() == bookingId) {
                    booking.setStatus(status);
                    booking.setAdminComments(comments);
                }

                String line = String.join(",",
                        booking.getStudentName(),
                        booking.getInstructorName(),
                        booking.getDate(),
                        booking.getTimeSlot(),
                        booking.getStatus(),
                        booking.getAdminComments() != null ? booking.getAdminComments() : "");
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete a booking by student, instructor, date, and timeSlot
    public void deleteBooking(String studentName, String instructorName, String date, String timeSlot) {
        List<Booking> bookings = getAllBookings();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Booking booking : bookings) {
                boolean match = booking.getStudentName().equalsIgnoreCase(studentName)
                        && booking.getInstructorName().equalsIgnoreCase(instructorName)
                        && booking.getDate().equals(date)
                        && booking.getTimeSlot().equals(timeSlot);

                if (!match) {
                    String line = String.join(",",
                            booking.getStudentName(),
                            booking.getInstructorName(),
                            booking.getDate(),
                            booking.getTimeSlot());
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get bookings by student name (optional)
    public List<Booking> getBookingsByStudent(String studentName) {
        List<Booking> studentBookings = new ArrayList<>();

        for (Booking booking : getAllBookings()) {
            if (booking.getStudentName().equalsIgnoreCase(studentName)) {
                studentBookings.add(booking);
            }
        }

        return studentBookings;
    }

    // pending bookings
    public List<Booking> getPendingBookings() {
        return getAllBookings().stream()
                .filter(b -> "PENDING".equals(b.getStatus()))
                .collect(Collectors.toList());
    }
}