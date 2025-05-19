package com.example.driving_school.serviceImpl;

import com.example.driving_school.entity.Instructor;
import com.example.driving_school.service.InstructorService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class InstructorServiceImpl implements InstructorService {
    private static final String FILE_PATH = "src/main/resources/data/instructors.txt";

    @Override
    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        instructors.add(new Instructor(
                                parts[0], parts[1],
                                Integer.parseInt(parts[2]),
                                Boolean.parseBoolean(parts[3])
                        ));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructors;
    }

    @Override
    public Instructor getInstructorById(String id) {
        return getAllInstructors().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveInstructor(Instructor instructor) {
        if (instructor.getId() == null || instructor.getId().isEmpty()) {
            instructor.setId("I" + System.currentTimeMillis() % 10000);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND)) {
            writer.write(instructor.toCsvString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstructor(Instructor updatedInstructor) {
        List<Instructor> instructors = getAllInstructors();
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId().equals(updatedInstructor.getId())) {
                instructors.set(i, updatedInstructor);
                break;
            }
        }
        saveAllInstructors(instructors);
    }

    @Override
    public void deleteInstructor(String id) {
        List<Instructor> instructors = getAllInstructors();
        instructors.removeIf(i -> i.getId().equals(id));
        saveAllInstructors(instructors);
    }

    @Override
    public List<Instructor> sortByExperience(boolean ascending) {
        List<Instructor> list = new ArrayList<>(getAllInstructors());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                boolean swap = ascending ?
                        list.get(j).getExperience() > list.get(j + 1).getExperience() :
                        list.get(j).getExperience() < list.get(j + 1).getExperience();
                if (swap) {
                    Instructor temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    @Override
    public List<Instructor> sortByName(boolean ascending) {
        List<Instructor> list = new ArrayList<>(getAllInstructors());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                int cmp = list.get(j).getName().compareToIgnoreCase(list.get(j + 1).getName());
                boolean swap = ascending ? cmp > 0 : cmp < 0;
                if (swap) {
                    Instructor temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    private void saveAllInstructors(List<Instructor> instructors) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Instructor i : instructors) {
                writer.write(i.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
