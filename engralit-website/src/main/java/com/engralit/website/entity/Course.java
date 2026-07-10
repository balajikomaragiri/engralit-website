package com.engralit.website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;          // "Target NET/SET English (Paper-II)"
    private String tagline;        // "Complete Preparation for UGC NET & SET Exams"

    private Double price;          // 4500.0
    private Integer durationDays;  // 60

    private String facultyName;    // "M. Balaji"
    private String mode;           // "100% Online, Recorded Classes"
    private String validity;       // "Till exam"
    private String contactNumber;  // "7799691771"
    private String thumbnailUrl;   // path to course banner image

    // One course has many syllabus units
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SyllabusUnit> syllabusUnits = new ArrayList<>();

    // One course has many highlighted features
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseFeature> features = new ArrayList<>();
}
