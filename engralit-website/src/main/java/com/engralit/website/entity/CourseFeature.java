package com.engralit.website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course_features")
@Getter
@Setter
@NoArgsConstructor
public class CourseFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String featureText;   // "Recorded Video Classes"
    private String iconClass;     // Bootstrap icon class, e.g. "bi-camera-video"

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
