package com.nexanova.timetable_service.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleSlotDTO {
    private String dayOfWeek;
    private LocalDate startTime;
    private LocalDate endTime;
    private Long trainerId;
    private Long moduleId;

}
