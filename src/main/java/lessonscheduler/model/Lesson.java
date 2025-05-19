package com.dulara.lessonscheduler.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lesson {

    private String startTime;
    private String endTime;
    private String lessonId;
    private String studentName;
    private String instructorName;
    private String date;
    private String time;
    private String status;
    private String lessonType;

    public Lesson(String lessonId, String studentName, String instructorName, String date, String time, String status, String lessonType){
        this.lessonId = lessonId;
        this.studentName = studentName;
        this.date = date;
        this.lessonType = lessonType;
        this.instructorName = instructorName;
        this.time = time;
        this.status = status;
    }

    public void confirmLesson() {
        this.status = "Confirmed";
    }

    // Method to cancel the lesson
    public void cancelLesson() {
        this.status = "Cancelled";
    }

    // Method to complete the lesson
    public void completeLesson() {
        this.status = "Completed";
    }

    public String getDate() {
        return date;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getStatus() {
        return status;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }
}

