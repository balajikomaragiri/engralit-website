package com.engralit.website.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "syllabus_units")
@Getter
@Setter
@NoArgsConstructor
public class SyllabusUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer unitNo;     // 1, 2, 3...
    private String unitTitle;   // "Drama", "Poetry" etc.

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
