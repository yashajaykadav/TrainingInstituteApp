package com.nexanova.enrollment_service.service;

import com.nexanova.enrollment_service.dto.EnrolledCourseDTO;
import com.nexanova.enrollment_service.entity.Enrollment;
import com.nexanova.enrollment_service.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Enrollment enrollStudent(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> enrollStudentsBulk(Long courseId, List<Long> studentIds) {
        if (courseId == null) throw new IllegalArgumentException("Course ID required");

        List<Enrollment> enrollments = new ArrayList<>();
        for (Long studentId : studentIds) {
            if (studentId != null) {
                enrollments.add(new Enrollment(studentId, courseId));
            }
        }
        return enrollmentRepository.saveAll(enrollments);
    }

    public List<Enrollment> getStudentEnrollments(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<EnrolledCourseDTO> getEnrolledCourses(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        List<EnrolledCourseDTO> courseDTOs = new ArrayList<>();

        for (Enrollment e : enrollments) {

            courseDTOs.add(new EnrolledCourseDTO(e.getCourseId(), "Course ID: " + e.getCourseId()));
        }
        return courseDTOs;
    }
}