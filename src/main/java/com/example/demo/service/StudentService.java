package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private static final String FILE_PATH = "data/students.txt";
    private static final String TEMP_FILE_PATH = "data/students_temp.txt";

    public StudentService() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Save a new student (append to file)
    public void saveStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.join(",",
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPassword(),
                    student.getRole(),
                    student.getPhone(),
                    student.getAddress());

            writer.write(line);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Failed to save student", e);
        }
    }

    // Load all students from the file
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 7) {
                    Student student = new Student(
                            parts[2], // email
                            parts[3], // password
                            parts[4], // role
                            parts[0], // firstName
                            parts[1], // lastName
                            parts[5], // phone
                            parts[6]  // address
                    );
                    students.add(student);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read students", e);
        }

        return students;
    }

    // Find a student by email (optimized version)
    public Student getStudentByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[2].equalsIgnoreCase(email)) {
                    return new Student(
                            parts[2], // email
                            parts[3], // password
                            parts[4], // role
                            parts[0], // firstName
                            parts[1], // lastName
                            parts[5], // phone
                            parts[6]  // address
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to find student", e);
        }
        return null;
    }

    // Authenticate student (plain text password comparison)
    public Student authenticate(String email, String password) {
        Student student = getStudentByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }

    // Update a student's record
    public void updateStudent(Student updatedStudent) {
        File originalFile = new File(FILE_PATH);
        File tempFile = new File(TEMP_FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updated = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[2].equalsIgnoreCase(updatedStudent.getEmail())) {
                    // Update the line
                    line = String.join(",",
                            updatedStudent.getFirstName(),
                            updatedStudent.getLastName(),
                            updatedStudent.getEmail(),
                            updatedStudent.getPassword(),
                            updatedStudent.getRole(),
                            updatedStudent.getPhone(),
                            updatedStudent.getAddress());
                    updated = true;
                }
                writer.write(line);
                writer.newLine();
            }

            if (!updated) {
                throw new RuntimeException("Student not found for update");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to update student", e);
        }

        // Replace original file with updated file
        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            throw new RuntimeException("Failed to complete student update");
        }
    }

    // Delete a student by email
    public void deleteStudent(String email) {
        File originalFile = new File(FILE_PATH);
        File tempFile = new File(TEMP_FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[2].equalsIgnoreCase(email)) {
                    found = true;
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            if (!found) {
                throw new RuntimeException("Student not found for deletion");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to delete student", e);
        }
        if (!originalFile.delete() || !tempFile.renameTo(originalFile)) {
            throw new RuntimeException("Failed to complete student deletion");
        }
    }

}