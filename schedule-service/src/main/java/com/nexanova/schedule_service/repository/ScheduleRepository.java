package com.nexanova.schedule_service.repository;

import com.nexanova.schedule_service.entity.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<WeeklySchedule,Long> {
}
