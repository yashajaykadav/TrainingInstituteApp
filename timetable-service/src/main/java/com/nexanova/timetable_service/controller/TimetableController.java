package com.nexanova.timetable_service.controller;

import com.nexanova.timetable_service.dto.ScheduleSlotDTO;
import com.nexanova.timetable_service.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/student/{studentId}")
    public List<ScheduleSlotDTO> getTimetable(@PathVariable Long studentId) {
        return timetableService.getStudentTimetable(studentId);
    }
}