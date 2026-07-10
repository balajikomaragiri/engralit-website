package com.engralit.website.controller;

import com.engralit.website.entity.Course;
import com.engralit.website.entity.Enrollment;
import com.engralit.website.entity.User;
import com.engralit.website.repository.CourseRepository;
import com.engralit.website.repository.EnrollmentRepository;
import com.engralit.website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // TEMPORARY test route — later this only runs AFTER Razorpay payment succeeds
    @GetMapping("/enroll-test/{courseId}")
    public String enrollTest(@PathVariable Long courseId, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        boolean alreadyEnrolled = enrollmentRepository.findByUserIdAndCourseId(user.getId(), courseId).isPresent();
        if (!alreadyEnrolled) {
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(user);
            enrollment.setCourse(course);
            enrollmentRepository.save(enrollment);
        }

        return "redirect:/dashboard";
    }
}