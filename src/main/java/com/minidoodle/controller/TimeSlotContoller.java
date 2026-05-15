package com.minidoodle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.service.TimeSlotService;

@RestController
@RequestMapping("")
public class TimeSlotContoller {

	@Autowired
	private TimeSlotService timeSlotService;
	
	@PostMapping("")
	private ResponseEntity<TimeSlotDto> addTimeSlot(@RequestBody TimeSlotDto timeSlotDto){
		
		TimeSlotDto addedTimeSlot = timeSlotService.addSlot(timeSlotDto);
		
		return ResponseEntity.ok(addedTimeSlot);
	}
	
}
