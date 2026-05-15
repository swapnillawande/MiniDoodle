package com.minidoodle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.entity.enums.SlotStatus;
import com.minidoodle.service.TimeSlotService;

@Service
public class TimeSlotServiceImpl implements TimeSlotService{

	@Override
	public List<TimeSlotDto> getAllSlots() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSlotDto getSlotById(Long slotId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSlotDto addSlot(TimeSlotDto timeSlotDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSlotDto updateSlotById(Long slotId, TimeSlotDto timeSlotDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSlotById(Long slotId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TimeSlotDto updateSlotStatus(Long slotId, SlotStatus status) {
		// TODO Auto-generated method stub
		return null;
	}



}
