package com.nexanova.enrollment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolledCourseDTO {
    private Long id;         // This will hold the COURSE ID
    private String courseName;
}