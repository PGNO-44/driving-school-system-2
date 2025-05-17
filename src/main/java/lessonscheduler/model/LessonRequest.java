package com.dulara.lessonscheduler.model;

public class LessonRequest {
    private String startTime; // ISO format
    private String endTime;
    private String requestId;
    private String studentName;
    private String preferredDate;  // Changed from 'date'
    private String preferredTime;  // Changed from 'time'

    // Constructor
    public LessonRequest(String requestId, String studentName, String preferredDate, String preferredTime) {
        this.requestId = requestId;
        this.studentName = studentName;
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
    }

    // Getters
    public String getStudentName() { return studentName; }
    public String getPreferredDate() { return preferredDate; }
    public String getPreferredTime() { return preferredTime; }
}
