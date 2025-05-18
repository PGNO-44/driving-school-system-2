package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService = new StudentService();

    // 1. List all students
    @GetMapping("")
    public String showStudentsPage(Model model) {
        model.addAttribute("studentsList", studentService.getAllStudents());
        return "students";
    }

    // 2. View details of a student
    @GetMapping("/view/{email}")
    public String viewStudentDetails(@PathVariable String email, Model model) {
        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details"; // This should be your student-details.jsp
        } else {
            model.addAttribute("errorMessage", "Student not found with email: " + email);
            return "error"; // Optional error.jsp
        }
    }

    // 3. Show the registration form
    @GetMapping("/register-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student()); // Needed for form binding
        return "student-register";
    }

    // 4. Handle registration form submission
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student, Model model) {
        studentService.saveStudent(student); // Save to students.txt
        return "redirect:/students"; // Redirect to the list page after registration
    }

    // 5. Show update form for a student
    @GetMapping("/update-form/{email}")
    public String showUpdateForm(@PathVariable String email, Model model) {
        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            model.addAttribute("student", student);
            return "update-student"; // update-student.jsp form
        } else {
            model.addAttribute("errorMessage", "Student not found with email: " + email);
            return "error";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        model.addAttribute("student", student);
        return "dashboard";
    }
}
