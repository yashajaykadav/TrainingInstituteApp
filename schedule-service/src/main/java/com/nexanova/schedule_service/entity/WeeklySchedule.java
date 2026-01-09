package com.nexanova.schedule_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class WeeklySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startOfWeek;

    @OneToMany(mappedBy = "weeklySchedule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScheduleSlot> slots;
}