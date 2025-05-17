package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Helper method to check authentication
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("loggedIn") != null;
    }

    // Home page (list all students)
    @GetMapping
    public String listStudents(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute("students", studentService.getAllStudents());
        return "student-list";
    }

    // Show create student form
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute("student", new Student());
        return "create-student";
    }

    // Handle create student
    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    // Show update student form
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "update-student";
    }

    // Handle update student
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    // Handle delete student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}