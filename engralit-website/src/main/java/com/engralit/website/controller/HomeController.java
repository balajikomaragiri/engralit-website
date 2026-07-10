package com.engralit.website.controller;

import com.engralit.website.entity.Course;
import com.engralit.website.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor   // Lombok auto-generates constructor for final fields (dependency injection)
public class HomeController {

    private final CourseRepository courseRepository;

    // Homepage: shows all courses
    @GetMapping("/")
    public String home(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "index";   // looks for templates/index.html
    }

    // Course detail page: shows syllabus + features for ONE course
    @GetMapping("/course/{id}")
    public String courseDetail(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        model.addAttribute("course", course);
        return "course-detail";  // looks for templates/course-detail.html
    }
}
