package com.nexanova.schedule_service.repository;

import com.nexanova.schedule_service.entity.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {

        @Query("""
                            SELECT COUNT(s)
                            FROM ScheduleSlot s
                            WHERE s.trainerId = :trainerId
                              AND s.dayOfWeek = :day
                              AND s.booked = true
                              AND s.startTime < :endTime
                              AND s.endTime > :startTime
                        """)
        long countTrainerConflicts(
                        @Param("trainerId") Long trainerId,
                        @Param("day") DayOfWeek day,
                        @Param("startTime") LocalTime startTime,
                        @Param("endTime") LocalTime endTime);
}
