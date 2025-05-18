package com.example.demo.service;

import com.example.demo.model.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingQueueService {
    private final BookingQueue bookingQueue = new BookingQueue();

    public synchronized void addBooking(Booking booking) {
        booking.setStatus("PENDING");
        bookingQueue.enqueue(booking);
    }

    public synchronized Booking getNextBooking() {
        return bookingQueue.dequeue();
    }

    public synchronized Booking[] viewAllPendingBookings() {
        return bookingQueue.toArray();
    }

    public synchronized boolean isEmpty() {
        return bookingQueue.isEmpty();
    }
}
