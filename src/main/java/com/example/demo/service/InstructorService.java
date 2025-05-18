package com.example.demo.service;

import com.example.demo.model.Instructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {

    private static final String FILE_PATH = "data/instructors.txt";

    public InstructorService() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String email = parts[2];
                    String password = parts[3];
                    String role = parts[4];
                    String address = parts[5];
                    String phoneNumber = parts[6];
                    double experience = Double.parseDouble(parts[7]);

                    Instructor instructor = new Instructor(email, password, role, firstName, lastName, address, phoneNumber, experience);
                    instructors.add(instructor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instructors;
    }

    public void saveInstructor(Instructor instructor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.join(",",
                    instructor.getFirstName(),
                    instructor.getLastName(),
                    instructor.getEmail(),
                    instructor.getPassword(),
                    instructor.getRole(),
                    instructor.getAddress(),
                    instructor.getPhoneNumber(),
                    String.valueOf(instructor.getExperience()));
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateInstructor(Instructor updatedInstructor) {
        List<Instructor> instructors = getAllInstructors();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Instructor instructor : instructors) {
                if (instructor.getEmail().equalsIgnoreCase(updatedInstructor.getEmail())) {
                    instructor = updatedInstructor;
                }

                String line = String.join(",",
                        instructor.getFirstName(),
                        instructor.getLastName(),
                        instructor.getEmail(),
                        instructor.getPassword(),
                        instructor.getRole(),
                        instructor.getAddress(),
                        instructor.getPhoneNumber(),
                        String.valueOf(instructor.getExperience()));
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteInstructor(String email) {
        List<Instructor> instructors = getAllInstructors();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Instructor instructor : instructors) {
                if (!instructor.getEmail().equalsIgnoreCase(email)) {
                    String line = String.join(",",
                            instructor.getFirstName(),
                            instructor.getLastName(),
                            instructor.getEmail(),
                            instructor.getPassword(),
                            instructor.getRole(),
                            instructor.getAddress(),
                            instructor.getPhoneNumber(),
                            String.valueOf(instructor.getExperience()));
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bubble sort instructors by experience (descending)
    public List<Instructor> sortByExperienceDescending() {
        List<Instructor> instructors = getAllInstructors();

        for (int i = 0; i < instructors.size() - 1; i++) {
            for (int j = 0; j < instructors.size() - i - 1; j++) {
                if (instructors.get(j).getExperience() < instructors.get(j + 1).getExperience()) {
                    Instructor temp = instructors.get(j);
                    instructors.set(j, instructors.get(j + 1));
                    instructors.set(j + 1, temp);
                }
            }
        }

        return instructors;
    }
}
