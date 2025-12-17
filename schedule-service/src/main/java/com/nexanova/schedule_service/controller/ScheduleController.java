package com.nexanova.schedule_service.controller;

import com.nexanova.schedule_service.dto.SlotAssignmentRequest;
import com.nexanova.schedule_service.entity.ScheduleSlot;
import com.nexanova.schedule_service.entity.WeeklySchedule;
import com.nexanova.schedule_service.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public WeeklySchedule createSchedule(@RequestParam("startDate") String startDate){

        LocalDate date = LocalDate.parse(startDate);

        return scheduleService.createBlankSchedule(date);

    }

    @PutMapping("/slot/{slotId}")
    public ScheduleSlot allocateSlot(@PathVariable Long slotId, @RequestBody SlotAssignmentRequest request){
        return  scheduleService.assignSlot(slotId,request.getTrainerId(),request.getModuleId());
    }

    @GetMapping("/{weekId}")
    public WeeklySchedule getSchedule(@PathVariable Long weekId) {
        return scheduleService.getScheduleById(weekId);
    }
}
