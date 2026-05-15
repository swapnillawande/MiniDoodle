package com.minidoodle.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.entity.TimeSlot;
import com.minidoodle.entity.enums.SlotStatus;
import com.minidoodle.repository.TimeSlotRepository;
import com.minidoodle.service.TimeSlotService;

@Service
public class TimeSlotServiceImpl implements TimeSlotService{

	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<TimeSlotDto> getAllSlots() {
		
		List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();
		
	    return allTimeSlots.stream()
	            .map(timeSlot -> modelMapper.map(timeSlot, TimeSlotDto.class))
	            .toList();
	}

	@Override
	public TimeSlotDto getSlotById(Long slotId) {
		
		return null;
	}

	@Override
	public TimeSlotDto addSlot(TimeSlotDto timeSlotDto) {
		
	    TimeSlot timeSlot = modelMapper.map(timeSlotDto, TimeSlot.class);

	    TimeSlot savedSlot = timeSlotRepository.save(timeSlot);

	    return modelMapper.map(savedSlot, TimeSlotDto.class);
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
