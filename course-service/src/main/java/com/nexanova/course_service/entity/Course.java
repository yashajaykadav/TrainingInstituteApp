package com.nexanova.course_service.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String description;
    private String duration;


    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Module> modules;
}
