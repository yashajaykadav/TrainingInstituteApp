package com.nexanova.enrollment_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long courseId;

    public Enrollment(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
