package com.dulara.lessonscheduler.util;

import com.dulara.lessonscheduler.model.LessonRequest;

public class CircularQueue {
    private final LessonRequest[] queue;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.queue = new LessonRequest[capacity];
        this.capacity = capacity;
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Adds a lesson request to the queue
     * @param item The lesson request to add
     * @return true if added successfully, false if queue is full
     */
    public boolean enqueue(LessonRequest item) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
        return true;
    }

    /**
     * Removes and returns the next lesson request
     * @return The next lesson request or null if empty
     */
    public LessonRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        LessonRequest item = queue[front];
        queue[front] = null; // Clear the reference
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    /**
     * Peeks at the next request without removing it
     * @return The next lesson request or null if empty
     */
    public LessonRequest peek() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public int remainingCapacity() {
        return capacity - size;
    }

    /**
     * Clears all requests from the queue
     */
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }
}