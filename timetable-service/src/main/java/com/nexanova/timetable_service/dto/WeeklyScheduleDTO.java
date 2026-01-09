package com.nexanova.timetable_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class WeeklyScheduleDTO {
    private Long id;
    private List<ScheduleSlotDTO> slots;
}