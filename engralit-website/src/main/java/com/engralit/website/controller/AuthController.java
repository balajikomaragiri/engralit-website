package com.engralit.website.controller;

import com.engralit.website.entity.User;
import com.engralit.website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Shows the login page (Spring Security handles the actual login logic)
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // templates/login.html
    }

    // Shows the signup form
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";  // templates/signup.html
    }

    // Handles signup form submission
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already registered. Please login instead.");
            return "signup";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));  // NEVER save plain text password
        user.setRole("STUDENT");
        userRepository.save(user);

        return "redirect:/login?registered=true";
    }
}