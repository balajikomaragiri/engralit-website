package com.engralit.website.repository;

import com.engralit.website.entity.CourseEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseEnquiryRepository extends JpaRepository<CourseEnquiry, Long> {

    // Search by name, email, or phone (used by admin search box)
    List<CourseEnquiry> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContaining(
            String name, String email, String phone);

    List<CourseEnquiry> findAllByOrderByCreatedAtDesc();
}