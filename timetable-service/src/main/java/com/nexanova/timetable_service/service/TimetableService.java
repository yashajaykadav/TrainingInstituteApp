package com.nexanova.timetable_service.service;

import com.nexanova.timetable_service.dto.EnrollmentDTO;
import com.nexanova.timetable_service.dto.ScheduleSlotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimetableService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ScheduleSlotDTO> getStudentTimetable(Long studentId){

        String enrollmentUrl = "http://localhost:8084/api/enrollment/student/"+studentId;
        EnrollmentDTO[] enrollments = restTemplate.getForObject(enrollmentUrl, EnrollmentDTO[].class);

        List<Long> courseId = Arrays.stream(enrollments)
                .map(EnrollmentDTO::getCourseId)
                .collect(Collectors.toList());


    }
}
