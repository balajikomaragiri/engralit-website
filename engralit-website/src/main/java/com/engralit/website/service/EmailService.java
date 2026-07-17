package com.engralit.website.service;

import com.engralit.website.entity.CourseEnquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.admin.email}")
    private String adminEmail;

    public void sendEnquiryNotification(CourseEnquiry enquiry) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("New Course Enquiry — " + enquiry.getCourseName());
        message.setText(
                "New enquiry received on Engralit website:\n\n" +
                        "Name: " + enquiry.getFullName() + "\n" +
                        "Email: " + enquiry.getEmail() + "\n" +
                        "Phone: " + enquiry.getPhone() + "\n" +
                        "WhatsApp: " + enquiry.getWhatsapp() + "\n" +
                        "State: " + enquiry.getState() + "\n" +
                        "City: " + enquiry.getCity() + "\n" +
                        "Qualification: " + enquiry.getQualification() + "\n" +
                        "Course: " + enquiry.getCourseName() + "\n" +
                        "Message: " + (enquiry.getMessage() != null ? enquiry.getMessage() : "—") + "\n"
        );
        mailSender.send(message);
    }
}