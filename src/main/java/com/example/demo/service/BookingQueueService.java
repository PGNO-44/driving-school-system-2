package com.example.demo.service;

import com.example.demo.model.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class BookingQueueService {
    private final Queue<Booking> bookingQueue = new LinkedList<>();

    public synchronized void addBooking(Booking booking) {
        booking.setStatus("PENDING");
        booking.setRequestTime(LocalDateTime.now());
        bookingQueue.add(booking);
    }

    public synchronized Booking getNextBooking() {
        return bookingQueue.poll();
    }

    public synchronized Queue<Booking> viewAllPendingBookings() {
        return new LinkedList<>(bookingQueue);
    }

    public synchronized boolean isEmpty() {
        return bookingQueue.isEmpty();
    }
}