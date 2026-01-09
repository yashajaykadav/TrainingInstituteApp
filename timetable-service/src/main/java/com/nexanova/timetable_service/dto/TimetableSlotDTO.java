package com.nexanova.timetable_service.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class TimetableSlotDTO {
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long moduleId;
    private Long trainerId;
}