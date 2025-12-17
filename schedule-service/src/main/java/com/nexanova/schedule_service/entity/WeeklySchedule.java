package com.nexanova.schedule_service.entity;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<ScheduleSlot> slots;

}
