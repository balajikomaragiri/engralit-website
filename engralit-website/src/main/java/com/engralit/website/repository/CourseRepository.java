package com.engralit.website.repository;

import com.engralit.website.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

// Extending JpaRepository gives you save(), findAll(), findById(), delete() for FREE
// No need to write SQL for basic operations
public interface CourseRepository extends JpaRepository<Course, Long> {
}
