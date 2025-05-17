package com.dulara.lessonscheduler.service;

import com.dulara.lessonscheduler.model.LessonRequest;
import com.dulara.lessonscheduler.model.Lesson;
import com.dulara.lessonscheduler.util.CircularQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LessonManager {

    private final CircularQueue lessonQueue;
    private final Lesson[] scheduledLessons;
    private int scheduledCount;
    private int lessonIdCounter = 1;
    private final FileHandler fileHandler;

    public LessonManager(@Value("${lesson.queue.size:100}") int queueSize,
                         @Value("${lesson.max.scheduled:50}") int maxLessons) {
        this.lessonQueue = new CircularQueue(queueSize);
        this.scheduledLessons = new Lesson[maxLessons];
        Arrays.fill(this.scheduledLessons, null);
        this.scheduledCount = 0;
        this.fileHandler = new FileHandler("lessons.txt");

        try {
            loadLessons();
            if (scheduledCount == 0) {
                initializeSampleLessons();
            }
        } catch (Exception e) {
            System.err.println("Error initializing lessons: " + e.getMessage());
            initializeSampleLessons(); // Fallback to sample lessons
        }

        System.out.println("[DEBUG] Initialized with scheduledCount: " + scheduledCount);
        System.out.println("[DEBUG] Sample lessons loaded: " + Arrays.toString(Arrays.copyOf(scheduledLessons, scheduledCount)));
    }

    private void initializeSampleLessons() {
        try {
            addSampleLesson("Math Student", "2024-05-20", "09:00");
            addSampleLesson("Science Student", "2024-05-21", "13:30");
            saveLessons();
        } catch (Exception e) {
            System.err.println("Error initializing sample lessons: " + e.getMessage());
        }
    }

    private void addSampleLesson(String studentName, String date, String time) {
        if (scheduledCount < scheduledLessons.length) {
            scheduledLessons[scheduledCount++] = new Lesson(
                    "L" + lessonIdCounter++,
                    Objects.requireNonNull(studentName),
                    "Instructor TBD",
                    Objects.requireNonNull(date),
                    Objects.requireNonNull(time),
                    "Pending",
                    "Regular"
            );
        }
    }


    public List<Lesson> getAllLessons() {
        Lesson testLesson = new Lesson(
                "L999",
                "Test Student",
                "Test Instructor",
                "2025-05-13",
                "14:00",
                "Active",
                "Demo"
        );
        return List.of(testLesson);
    }

    public void addRequest(String studentName, String date, String time) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (date == null || time == null) {
            throw new IllegalArgumentException("Date and time must be specified");
        }

        lessonQueue.enqueue(new LessonRequest(
                "REQ-" + System.currentTimeMillis(),
                studentName,
                date,
                time
        ));
    }

    public Lesson processNextRequest() {
        LessonRequest request = lessonQueue.dequeue();
        if (request == null || scheduledCount >= scheduledLessons.length) {
            return null;
        }

        Lesson newLesson = new Lesson(
                "L" + lessonIdCounter++,
                request.getStudentName(),
                "Instructor TBD",
                request.getPreferredDate(),
                request.getPreferredTime(),
                "Pending",
                "Regular"
        );

        scheduledLessons[scheduledCount++] = newLesson;
        saveLessons();
        return newLesson;
    }

    public boolean cancelLesson(String lessonId) {
        for (int i = 0; i < scheduledCount; i++) {
            if (scheduledLessons[i] != null &&
                    scheduledLessons[i].getLessonId().equals(lessonId)) {
                // Shift remaining elements left
                for (int j = i; j < scheduledCount - 1; j++) {
                    scheduledLessons[j] = scheduledLessons[j + 1];
                }
                scheduledLessons[--scheduledCount] = null; // Clear last slot
                saveLessons();
                return true;
            }
        }
        return false;
    }

    public void sortLessonsByDateBubbleSort() {
        for (int i = 0; i < scheduledCount - 1; i++) {
            for (int j = 0; j < scheduledCount - i - 1; j++) {
                if (scheduledLessons[j].getDate().compareTo(scheduledLessons[j + 1].getDate()) > 0) {
                    // Swap lessons
                    Lesson temp = scheduledLessons[j];
                    scheduledLessons[j] = scheduledLessons[j + 1];
                    scheduledLessons[j + 1] = temp;
                }
            }
        }
    }

    private void saveLessons() {
        fileHandler.saveToFile(scheduledLessons, scheduledCount);
    }

    private void loadLessons() {
        try {
            Lesson[] loaded = fileHandler.loadFromFile(scheduledLessons.length);
            System.out.println("[DEBUG] Loaded lessons from file: " + Arrays.toString(loaded));
            if (loaded != null) {
                System.arraycopy(loaded, 0, scheduledLessons, 0, loaded.length);
                scheduledCount = loaded.length;

                // Safe ID counter update
                lessonIdCounter = Arrays.stream(scheduledLessons)
                        .filter(Objects::nonNull)
                        .filter(lesson -> lesson.getLessonId() != null)
                        .mapToInt(lesson -> {
                            try {
                                return Integer.parseInt(lesson.getLessonId().substring(1)) + 1;
                            } catch (Exception e) {
                                return 1; // Default if parsing fails
                            }
                        })
                        .max()
                        .orElse(1); // Default if no lessons
            }
        } catch (Exception e) {
            System.err.println("Error loading lessons: " + e.getMessage());
            scheduledCount = 0; // Reset if load fails
        }
    }
}