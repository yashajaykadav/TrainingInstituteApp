package com.nexanova.schedule_service.repository;

import com.nexanova.schedule_service.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot,Long> {
}
