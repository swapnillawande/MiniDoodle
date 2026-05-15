package com.minidoodle.service;

import java.util.List;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.entity.enums.SlotStatus;


public interface TimeSlotService {

	List<TimeSlotDto> getAllSlots();

	TimeSlotDto getSlotById(Long slotId);

	TimeSlotDto addSlot(TimeSlotDto timeSlotDto);

	TimeSlotDto updateSlotById(Long slotId, TimeSlotDto timeSlotDto);

	void deleteSlotById(Long slotId);

	TimeSlotDto updateSlotStatus(Long slotId, SlotStatus status);
	
	void deleteAllTimeSlots();
}
