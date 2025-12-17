package com.nexanova.enrollment_service.service;


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

    public List<Enrollment> enrollStudentsBulk(Long courseId,List<Long> studentIds)
    {
        List<Enrollment> enrollments = new ArrayList<>();
        for(Long sId :studentIds){
            enrollments.add(new Enrollment(sId,courseId));
        }
        return enrollmentRepository.saveAll(enrollments);
    }

    public List<Enrollment> getStudentEnrollments(Long studentId)
    {
        return enrollmentRepository.findByStudentId(studentId);
    }

}
