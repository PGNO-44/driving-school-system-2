package com.dulara.lessonscheduler.service;

import com.dulara.lessonscheduler.model.Lesson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LessonManager {
    private final Lesson[] lessons;
    private int lessonCount;
    private int lessonIdCounter = 1;
    private final int maxLessons;

    public LessonManager(@Value("${lesson.max.scheduled:50}") int maxLessons) {
        this.maxLessons = maxLessons;
        this.lessons = new Lesson[maxLessons];
        this.lessonCount = 0;
        initializeSampleLessons();
    }

    private void initializeSampleLessons() {
        addLesson("Dulara", "2024-05-20", "09:00");
        addLesson("Sandaru", "2024-05-21", "13:30");
    }

    // CREATE operation
    public boolean addLesson(String studentName, String date, String time) {
        if (lessonCount >= maxLessons) {
            return false;
        }

        lessons[lessonCount++] = new Lesson(
            "L" + lessonIdCounter++,
            studentName,
            "Instructor TBD",
            date,
            time,
            "Pending",
            "Regular"
        );
        return true;
    }

    // READ operation (basic version)
    public Lesson[] getAllLessons() {
        Lesson[] result = new Lesson[lessonCount];
        System.arraycopy(lessons, 0, result, 0, lessonCount);
        return result;
    }

    // DELETE operation
    public boolean cancelLesson(String lessonId) {
        for (int i = 0; i < lessonCount; i++) {
            if (lessons[i] != null && lessons[i].getLessonId().equals(lessonId)) {
                // Shift remaining elements left
                for (int j = i; j < lessonCount - 1; j++) {
                    lessons[j] = lessons[j + 1];
                }
                lessons[--lessonCount] = null;
                return true;
            }
        }
        return false;
    }
}
