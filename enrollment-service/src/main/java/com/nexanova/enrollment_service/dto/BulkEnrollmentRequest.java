package com.nexanova.enrollment_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class BulkEnrollmentRequest {

private Long courseId;

private List<Long> studentIds;
}
