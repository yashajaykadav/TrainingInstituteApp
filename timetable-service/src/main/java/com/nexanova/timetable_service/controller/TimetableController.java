package com.nexanova.timetable_service.controller;

import com.nexanova.timetable_service.dto.TimetableSlotDTO;
import com.nexanova.timetable_service.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/student/{studentId}")
    public List<TimetableSlotDTO> getStudentTimetable(@PathVariable Long studentId) {
        return timetableService.getStudentTimetable(studentId);
    }
}