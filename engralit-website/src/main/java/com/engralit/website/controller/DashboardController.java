package com.engralit.website.controller;

import com.engralit.website.entity.Enrollment;
import com.engralit.website.entity.User;
import com.engralit.website.repository.EnrollmentRepository;
import com.engralit.website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Enrollment> enrollments = enrollmentRepository.findByUserId(user.getId());

        model.addAttribute("email", email);
        model.addAttribute("enrollments", enrollments);
        return "dashboard";
    }
}