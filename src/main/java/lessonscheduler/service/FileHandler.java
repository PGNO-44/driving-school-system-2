package com.dulara.lessonscheduler.service;

import com.dulara.lessonscheduler.model.Lesson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Save lessons as JSON for better compatibility
    public void saveToFile(Lesson[] lessons, int count) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                if (lessons[i] != null) {
                    writer.println(convertToJson(lessons[i]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving lessons: " + e.getMessage());
        }
    }

    // Load lessons from JSON
    public Lesson[] loadFromFile(int maxCapacity) {
        List<Lesson> loadedLessons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null && loadedLessons.size() < maxCapacity) {
                Lesson lesson = parseFromJson(line);
                if (lesson != null) {
                    loadedLessons.add(lesson);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading lessons: " + e.getMessage());
        }
        return loadedLessons.toArray(new Lesson[0]);
    }

    private String convertToJson(Lesson lesson) {
        // Simple JSON conversion - implement proper escaping if needed
        return String.format(
                "{\"lessonId\":\"%s\",\"studentName\":\"%s\",\"instructor\":\"%s\"," +
                        "\"date\":\"%s\",\"time\":\"%s\",\"status\":\"%s\",\"type\":\"%s\"}",
                lesson.getLessonId(),
                lesson.getStudentName(),
                lesson.getInstructorName(),
                lesson.getDate(),
                lesson.getTime(),
                lesson.getStatus(),
                lesson.getLessonType()
        );
    }

    private Lesson parseFromJson(String json) {
        // Simple JSON parsing - consider using Gson/Jackson for production
        try {
            String[] parts = json.replaceAll("[{}\"]", "").split(",");
            String[] fields = new String[7];
            for (String part : parts) {
                String[] kv = part.split(":");
                if (kv.length == 2) {
                    String key = kv[0].trim();
                    String value = kv[1].trim();
                    switch (key) {
                        case "lessonId": fields[0] = value; break;
                        case "studentName": fields[1] = value; break;
                        case "instructor": fields[2] = value; break;
                        case "date": fields[3] = value; break;
                        case "time": fields[4] = value; break;
                        case "status": fields[5] = value; break;
                        case "type": fields[6] = value; break;
                    }
                }
            }
            return new Lesson(fields[0], fields[1], fields[2], fields[3],
                    fields[4], fields[5], fields[6]);
        } catch (Exception e) {
            System.err.println("Error parsing lesson: " + json);
            return null;
        }
    }
}