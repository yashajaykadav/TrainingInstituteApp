package com.nexanova.timetable_service.service;

import com.nexanova.timetable_service.dto.EnrollmentDTO;
import com.nexanova.timetable_service.dto.ScheduleSlotDTO;
import com.nexanova.timetable_service.dto.WeeklyScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TimetableService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ScheduleSlotDTO> getStudentTimetable(Long studentId) {
        try {
            // 1. Get Enrollments
            String enrollmentUrl = "http://localhost:8084/api/enrollments/student/" + studentId;
            EnrollmentDTO[] enrollments = restTemplate.getForObject(enrollmentUrl, EnrollmentDTO[].class);

            if (enrollments == null || enrollments.length == 0) {
                System.out.println("No enrollments found for student: " + studentId);
                return new ArrayList<>();
            }

            // 2. Get Schedule (Hardcoded ID 1 - ensure this exists in Schedule DB!)
            String scheduleUrl = "http://localhost:8083/api/schedule/1";
            WeeklyScheduleDTO weeklySchedule = restTemplate.getForObject(scheduleUrl, WeeklyScheduleDTO.class);

            if (weeklySchedule == null || weeklySchedule.getSlots() == null) {
                System.out.println("Weekly schedule is null or empty!");
                return new ArrayList<>();
            }

            // 3. Filter Logic
            return weeklySchedule.getSlots().stream()
                    .filter(slot -> slot.getModuleId() != null)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            // This prints the REAL error to your IntelliJ console
            e.printStackTrace();
            throw new RuntimeException("Error fetching timetable: " + e.getMessage());
        }
    }
}