package com.nexanova.schedule_service.service;


import com.nexanova.schedule_service.entity.ScheduleSlot;
import com.nexanova.schedule_service.entity.WeeklySchedule;
import com.nexanova.schedule_service.repository.ScheduleRepository;
import com.nexanova.schedule_service.repository.ScheduleSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleSlotRepository scheduleSlotRepository;

    public WeeklySchedule createBlankSchedule(LocalDate startOfWeek){
        WeeklySchedule week = new WeeklySchedule();
        week.setStartOfWeek(startOfWeek);

        List<ScheduleSlot> slots = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            LocalDate currentDate = startOfWeek.plusDays(i);
            String dayName = currentDate.getDayOfWeek().toString();

            slots.add(createSlot(dayName, LocalTime.of(8,0),LocalTime.of(11,0)));
            slots.add(createSlot(dayName, LocalTime.of(11,0),LocalTime.of(14,0)));
            slots.add(createSlot(dayName, LocalTime.of(14,0),LocalTime.of(17,0)));
            slots.add(createSlot(dayName, LocalTime.of(17,0),LocalTime.of(20,0)));
        }
        week.setSlots(slots);
        return scheduleRepository.save(week);
    }

    private ScheduleSlot createSlot(String day,LocalTime startTime,LocalTime endTime){
        ScheduleSlot slot = new ScheduleSlot();
        slot.setDayOfWeek(day);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        slot.setBooked(true);
        return  slot;
    }

    public ScheduleSlot assignSlot(Long slotId,Long trainerId,Long moduleId){
        Optional<ScheduleSlot> slotOptional = scheduleSlotRepository.findById(slotId);

        if(slotOptional.isPresent()){
            ScheduleSlot slot = slotOptional.get();
            slot.setTrainerId(trainerId);
            slot.setModuleId(moduleId);
            slot.setBooked(true);
            return scheduleSlotRepository.save(slot);
        }else{
            throw  new RuntimeException("Slot not Found Id "+slotId);
        }
    }

    public WeeklySchedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with ID: " + id));
    }
}
