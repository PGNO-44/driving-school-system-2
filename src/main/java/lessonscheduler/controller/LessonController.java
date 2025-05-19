package com.dulara.lessonscheduler.controller;

import com.dulara.lessonscheduler.model.Lesson;
import com.dulara.lessonscheduler.model.LessonRequest;
import com.dulara.lessonscheduler.service.LessonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonManager lessonManager;

    @Autowired
    public LessonController(LessonManager lessonManager) {
        this.lessonManager = lessonManager;
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        try {
            List<Lesson> lessons = lessonManager.getAllLessons();
            return ResponseEntity.ok(lessons);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/process")
    public ResponseEntity<String> processNextRequest() {
        try {
            lessonManager.processNextRequest();
            return ResponseEntity.ok("Processed the next request");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelLesson(@PathVariable String id) {
        try {
            lessonManager.cancelLesson(id);
            return ResponseEntity.ok("Lesson " + id + " cancelled.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to cancel lesson: " + e.getMessage());
        }
    }
}