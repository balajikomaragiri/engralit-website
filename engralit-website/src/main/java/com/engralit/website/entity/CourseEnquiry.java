package com.engralit.website.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_enquiries")
@Getter
@Setter
@NoArgsConstructor
public class CourseEnquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Enter a valid 10-digit phone number")
    private String phone;

    @Pattern(regexp = "^[0-9]{10}$|^$", message = "Enter a valid 10-digit WhatsApp number")
    private String whatsapp;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    private String courseName;

    private Long courseId;

    @Column(length = 1000)
    private String message;

    private String status = "NEW";   // NEW or CONTACTED

    private LocalDateTime createdAt = LocalDateTime.now();
}