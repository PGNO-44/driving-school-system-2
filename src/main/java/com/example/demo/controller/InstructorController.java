package com.example.demo.controller;

import com.example.demo.model.Instructor;
import com.example.demo.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
@CrossOrigin
public class InstructorController {

    private final InstructorService instructorService = new InstructorService();

    @PostMapping("/register")
    public ResponseEntity<String>  registerInstructor(@RequestBody Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return ResponseEntity.ok("Instructor registered successfully");
    }

    @GetMapping("")
    public String getAllInstructors(Model model) {
        model.addAttribute("instructorsList", instructorService.getAllInstructors());
        return "instructors";
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Instructor>> getAllInstructorsSorted() {
        return ResponseEntity.ok(instructorService.sortByExperienceDescending());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteInstructor(@PathVariable String email) {
        instructorService.deleteInstructor(email);
        return ResponseEntity.ok("Instructor deleted successfully");
    }
}
