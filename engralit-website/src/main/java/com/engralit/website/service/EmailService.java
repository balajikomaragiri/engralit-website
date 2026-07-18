package com.engralit.website.service;

import com.engralit.website.entity.CourseEnquiry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @PostConstruct
    public void init() {
        // Trim any accidental whitespace from environment variables
        adminEmail = adminEmail == null ? null : adminEmail.trim();
        fromEmail = fromEmail == null ? null : fromEmail.trim();
        log.info("EmailService initialized. adminEmail='{}', fromEmail='{}'", adminEmail, fromEmail);
    }

    public void sendEnquiryNotification(CourseEnquiry enquiry) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(adminEmail);
        message.setSubject("New Course Enquiry — " + safe(enquiry.getCourseName()));
        message.setText(
                "New enquiry received on Engralit website:\n\n" +
                        "Name: " + safe(enquiry.getFullName()) + "\n" +
                        "Email: " + safe(enquiry.getEmail()) + "\n" +
                        "Phone: " + safe(enquiry.getPhone()) + "\n" +
                        "WhatsApp: " + safe(enquiry.getWhatsapp()) + "\n" +
                        "State: " + safe(enquiry.getState()) + "\n" +
                        "City: " + safe(enquiry.getCity()) + "\n" +
                        "Qualification: " + safe(enquiry.getQualification()) + "\n" +
                        "Course: " + safe(enquiry.getCourseName()) + "\n" +
                        "Message: " + (enquiry.getMessage() != null ? enquiry.getMessage() : "—") + "\n"
        );
        mailSender.send(message);
    }

    // Strips newlines/carriage returns to prevent header injection or parse issues,
    // and guards against nulls
    private String safe(String value) {
        if (value == null) return "";
        return value.replace("\r", " ").replace("\n", " ").trim();
    }
}