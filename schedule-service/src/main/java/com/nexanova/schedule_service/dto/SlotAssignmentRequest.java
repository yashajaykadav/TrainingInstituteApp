package com.nexanova.schedule_service.dto;

import lombok.Data;

@Data
public class SlotAssignmentRequest {
    private Long trainerId;
    private Long moduleId;
}
