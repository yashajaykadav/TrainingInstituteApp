package com.nexanova.schedule_service.service;

import com.nexanova.schedule_service.entity.ScheduleSlot;
import com.nexanova.schedule_service.entity.WeeklySchedule;
import com.nexanova.schedule_service.repository.ScheduleRepository;
import com.nexanova.schedule_service.repository.ScheduleSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleSlotRepository scheduleSlotRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleSlotRepository = scheduleSlotRepository;
    }

    public WeeklySchedule createBlankSchedule(LocalDate startOfWeek) {
        scheduleRepository.findByStartOfWeek(startOfWeek)
                .ifPresent(s -> {
                    throw new IllegalStateException(
                            "Schedule already exists for week starting " + startOfWeek);
                });

        WeeklySchedule week = new WeeklySchedule();
        week.setStartOfWeek(startOfWeek);

        List<ScheduleSlot> slots = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            DayOfWeek day = startOfWeek.plusDays(i).getDayOfWeek();

            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                continue;
            }

            slots.add(createSlot(week, day, LocalTime.of(8, 0), LocalTime.of(11, 0)));
            slots.add(createSlot(week, day, LocalTime.of(11, 0), LocalTime.of(14, 0)));
            slots.add(createSlot(week, day, LocalTime.of(14, 0), LocalTime.of(17, 0)));
            slots.add(createSlot(week, day, LocalTime.of(17, 0), LocalTime.of(20, 0)));
        }

        week.setSlots(slots);
        return scheduleRepository.save(week);
    }

    private ScheduleSlot createSlot(WeeklySchedule week, DayOfWeek day, LocalTime start, LocalTime end) {
        ScheduleSlot slot = new ScheduleSlot();
        slot.setWeeklySchedule(week);
        slot.setDayOfWeek(day);
        slot.setStartTime(start);
        slot.setEndTime(end);
        slot.setBooked(false);
        return slot;
    }

    public ScheduleSlot assignSlot(Long slotId, Long trainerId, Long moduleId) {
        ScheduleSlot slot = scheduleSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found: " + slotId));

        long conflicts = scheduleSlotRepository.countTrainerConflicts(
                trainerId,
                slot.getDayOfWeek(),
                slot.getStartTime(),
                slot.getEndTime());

        if (conflicts > 0) {
            throw new IllegalStateException("Trainer " + trainerId + " has a conflicting slot");
        }

        slot.setTrainerId(trainerId);
        slot.setModuleId(moduleId);
        slot.setBooked(true);

        return scheduleSlotRepository.save(slot);
    }

    public WeeklySchedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found: " + id));
    }
}