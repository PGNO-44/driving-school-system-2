package com.example.driving_school.service;

import com.example.driving_school.entity.Instructor;
import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(String id);
    void saveInstructor(Instructor instructor);
    void updateInstructor(Instructor instructor);
    void deleteInstructor(String id);
    List<Instructor> sortByExperience(boolean ascending);
    List<Instructor> sortByName(boolean ascending);
}
