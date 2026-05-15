package com.minidoodle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.service.TimeSlotService;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotContoller {

	@Autowired
	private TimeSlotService timeSlotService;
	
	@PostMapping("")
	private ResponseEntity<TimeSlotDto> addTimeSlot(@RequestBody TimeSlotDto timeSlotDto){
		
		TimeSlotDto addedTimeSlot = timeSlotService.addSlot(timeSlotDto);
		
		return ResponseEntity.ok(addedTimeSlot);
	}
	
	@GetMapping("")
	private ResponseEntity<List<TimeSlotDto>> getAllTimeSlot(){
		
		List<TimeSlotDto> allSlots = timeSlotService.getAllSlots();
		return ResponseEntity.ok(allSlots);
		
	}
	
	@GetMapping("/{slotId}")
	private ResponseEntity<TimeSlotDto> getTimeSlotById(@PathVariable("slotId") Long slotId){
		
		return ResponseEntity.ok(timeSlotService.getSlotById(slotId));
	}
	
	
	@PutMapping("/{slotId}")
	private ResponseEntity<TimeSlotDto> updateTimeSlotById(
			@PathVariable("slotId") Long slotId,
			@RequestBody TimeSlotDto timeSlotDto
			){
		
		return ResponseEntity.ok(timeSlotService.updateSlotById(slotId, timeSlotDto));
	}
	
	
	
}
