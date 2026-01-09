package com.nexanova.timetable_service.service;

import com.nexanova.timetable_service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private RestTemplate restTemplate;

    // CHECK THESE PORTS!
    // If your Enrollment Service runs on 8081 or 8082, change 8084 below.
    private static final String ENROLLMENT_SERVICE = "http://localhost:8084/api/enrollments/student/%d/courses";
    private static final String SCHEDULE_SERVICE = "http://localhost:8083/api/schedule/course/%d";

    public List<TimetableSlotDTO> getStudentTimetable(Long studentId) {

        // 1. Fetch Course ID from Enrollment Service
        CourseDTO[] courses = null;
        try {
            String url = String.format(ENROLLMENT_SERVICE, studentId);
            courses = restTemplate.getForObject(url, CourseDTO[].class);
        } catch (Exception e) {
            System.err.println("❌ ERROR Connecting to Enrollment Service: " + e.getMessage());
            return new ArrayList<>(); // Return empty if service is down
        }

        if (courses == null || courses.length == 0) {
            System.out.println("ℹ️ No enrollments found for student " + studentId);
            return new ArrayList<>();
        }

        Long courseId = courses[0].getId();

        // 2. Fetch Schedule from Schedule Service
        WeeklyScheduleDTO weeklySchedule = null;
        try {
            String url = String.format(SCHEDULE_SERVICE, courseId);
            weeklySchedule = restTemplate.getForObject(url, WeeklyScheduleDTO.class);
        } catch (Exception e) {
            System.err.println("❌ ERROR Connecting to Schedule Service: " + e.getMessage());
            return new ArrayList<>();
        }

        if (weeklySchedule == null || weeklySchedule.getSlots() == null) {
            return new ArrayList<>();
        }

        // 3. Map to DTO
        List<TimetableSlotDTO> timetable = new ArrayList<>();
        for (ScheduleSlotDTO slot : weeklySchedule.getSlots()) {
            if (slot.getModuleId() == null || !slot.isBooked()) continue;

            TimetableSlotDTO dto = new TimetableSlotDTO();
            dto.setDayOfWeek(slot.getDayOfWeek());
            dto.setStartTime(slot.getStartTime());
            dto.setEndTime(slot.getEndTime());
            dto.setModuleId(slot.getModuleId());
            dto.setTrainerId(slot.getTrainerId());

            timetable.add(dto);
        }
        return timetable;
    }
}