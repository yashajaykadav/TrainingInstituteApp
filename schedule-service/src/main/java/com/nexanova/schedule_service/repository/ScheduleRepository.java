package com.nexanova.schedule_service.repository;

import com.nexanova.schedule_service.entity.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<WeeklySchedule, Long> {

    Optional<WeeklySchedule> findByStartOfWeek(LocalDate startOfWeek);
}
