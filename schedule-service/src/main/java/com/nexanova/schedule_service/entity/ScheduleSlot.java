package com.nexanova.schedule_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "weekly_schedule_id", "day_of_week", "start_time" })
})
public class ScheduleSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    private Long trainerId;
    private Long moduleId;

    @Column(name = "is_booked",nullable = false)
    private boolean booked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_schedule_id", nullable = false)
    @JsonBackReference
    private WeeklySchedule weeklySchedule;
}