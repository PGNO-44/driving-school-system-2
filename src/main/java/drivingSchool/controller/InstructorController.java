package com.example.driving_school.controller;

import com.example.driving_school.entity.Instructor;
import com.example.driving_school.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String showInstructors(Model model) {
        model.addAttribute("instructors", instructorService.getAllInstructors());
        model.addAttribute("instructor", new Instructor());
        return "instructors";
    }

    @PostMapping("/register")
    public String registerInstructor(@ModelAttribute Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public Instructor getInstructor(@PathVariable String id) {
        return instructorService.getInstructorById(id);
    }

    @PostMapping("/edit")
    public String updateInstructor(@ModelAttribute Instructor instructor) {
        instructorService.updateInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable String id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }

    @GetMapping("/sort")
    public String sortInstructors(@RequestParam String criteria,
                                  @RequestParam boolean ascending,
                                  Model model) {
        List<Instructor> sorted = "experience".equalsIgnoreCase(criteria) ?
                instructorService.sortByExperience(ascending) :
                instructorService.sortByName(ascending);

        model.addAttribute("instructors", sorted);
        return "instructors";
    }
}
