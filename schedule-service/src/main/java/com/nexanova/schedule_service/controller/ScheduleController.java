package com.nexanova.schedule_service.controller;

import com.nexanova.schedule_service.dto.SlotAssignmentRequest;
import com.nexanova.schedule_service.entity.ScheduleSlot;
import com.nexanova.schedule_service.entity.WeeklySchedule;
import com.nexanova.schedule_service.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestParam("startDate") String startDate) {
        try {
            LocalDate date = LocalDate.parse(startDate);
            WeeklySchedule schedule = scheduleService.createBlankSchedule(date);
            return ResponseEntity.ok(schedule);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Server Error: " + e.getMessage());
        }
    }

    @PutMapping("/slot/{slotId}")
    public ScheduleSlot allocateSlot(@PathVariable Long slotId, @RequestBody SlotAssignmentRequest request) {
        return scheduleService.assignSlot(slotId, request.getTrainerId(), request.getModuleId());
    }

    @GetMapping("/{weekId}")
    public WeeklySchedule getSchedule(@PathVariable Long weekId) {
        return scheduleService.getScheduleById(weekId);
    }
    @GetMapping("/course/{courseId}")
    public WeeklySchedule getScheduleByCourse(@PathVariable Long courseId) {
        return scheduleService.getScheduleById(1L);
    }
}