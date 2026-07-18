package com.engralit.website.controller;

import com.engralit.website.entity.Course;
import com.engralit.website.entity.CourseEnquiry;
import com.engralit.website.repository.CourseEnquiryRepository;
import com.engralit.website.repository.CourseRepository;
import com.engralit.website.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EnquiryController {

    private final CourseRepository courseRepository;
    private final CourseEnquiryRepository enquiryRepository;
    private final EmailService emailService;

    // Shows the enquiry form
    @GetMapping("/enquiry/{courseId}")
    public String showForm(@PathVariable Long courseId,
                           Model model,
                           Authentication authentication) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseEnquiry enquiry = new CourseEnquiry();

        enquiry.setCourseId(course.getId());
        enquiry.setCourseName(course.getTitle());

        // Get correct email for Google OAuth login or normal login
        String email;

        if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            email = oauth2User.getAttribute("email");
        } else {
            email = authentication.getName();
        }

        enquiry.setEmail(email);

        model.addAttribute("enquiry", enquiry);
        model.addAttribute("course", course);

        return "enquiry-form";
    }

    // Handles form submission
    @PostMapping("/enquiry")
    public String submitForm(
            @Valid @ModelAttribute("enquiry") CourseEnquiry enquiry,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "course",
                    courseRepository
                            .findById(enquiry.getCourseId())
                            .orElse(null)
            );

            return "enquiry-form";
        }

        // Save enquiry to database (this must always succeed for the user to get credit for their submission)
        enquiryRepository.save(enquiry);

        // Try to send admin notification email — if this fails, don't crash the user's request.
        // The enquiry is already saved, so failing to notify the admin shouldn't show the user an error.
        try {
            emailService.sendEnquiryNotification(enquiry);
        } catch (Exception e) {
            log.error("Failed to send enquiry notification email for enquiry id={}", enquiry.getId(), e);
        }

        return "redirect:/enquiry/success";
    }

    @GetMapping("/enquiry/success")
    public String successPage() {
        return "enquiry-success";
    }
}