package com.nexanova.enrollment_service.controller;


import com.nexanova.enrollment_service.dto.BulkEnrollmentRequest;
import com.nexanova.enrollment_service.entity.Enrollment;
import com.nexanova.enrollment_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public Enrollment enroll(@RequestBody Enrollment enrollment){
        return enrollmentService.enrollStudent(enrollment);
    }

    @PostMapping("/bulk")
    public List<Enrollment> enrollBulk(@RequestBody BulkEnrollmentRequest request){
        return enrollmentService.enrollStudentsBulk(request.getCourseId(),request.getStudentIds());
    }

    @GetMapping("student/{studentId}")
    public List<Enrollment>getEnrollments(@PathVariable Long studentId){
        return enrollmentService.getStudentEnrollments(studentId);
    }
}
