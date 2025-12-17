package com.nexanova.timetable_service.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleSlotDTO {
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long trainerId;
    private Long moduleId;

}
