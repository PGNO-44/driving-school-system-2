package com.example.demo.service;

import com.example.demo.model.Booking;

public class BookingQueue {
    private static class Node {
        Booking data;
        Node next;

        Node(Booking data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public BookingQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Booking booking) {
        Node newNode = new Node(booking);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public Booking dequeue() {
        if (front == null) {
            return null;
        }
        Booking booking = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return booking;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public Booking[] toArray() {
        int size = size();
        Booking[] array = new Booking[size];
        Node current = front;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    public int size() {
        int count = 0;
        Node current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
