package com.minidoodle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.entity.enums.SlotStatus;
import com.minidoodle.service.TimeSlotService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

	private static final Logger logger = LoggerFactory.getLogger(TimeSlotController.class);

	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@PostMapping("")
	public ResponseEntity<TimeSlotDto> addTimeSlot(@RequestBody TimeSlotDto timeSlotDto){
		logger.info("POST TIME SLOT API CALLED");
		
		TimeSlotDto addedTimeSlot = timeSlotService.addSlot(timeSlotDto);
		
		return ResponseEntity.ok(addedTimeSlot);
	}
	
	@GetMapping("")
	public ResponseEntity<List<TimeSlotDto>> getAllTimeSlot(){
		logger.info("GET ALL TIME SLOT API CALLED");
		
		List<TimeSlotDto> allSlots = timeSlotService.getAllSlots();
		return ResponseEntity.ok(allSlots);
		
	}
	
	@GetMapping("/{slotId}")
	public ResponseEntity<TimeSlotDto> getTimeSlotById(@PathVariable("slotId") Long slotId){
		logger.info("GET TIME SLOT BY ID API CALLED");

		return ResponseEntity.ok(timeSlotService.getSlotById(slotId));
	}
	
	
	@PutMapping("/{slotId}")
	public ResponseEntity<TimeSlotDto> updateTimeSlotById(
			@PathVariable("slotId") Long slotId,
			@RequestBody TimeSlotDto timeSlotDto
			){
		logger.info("UPDATE TIME SLOT BY ID API CALLED");

		return ResponseEntity.ok(timeSlotService.updateSlotById(slotId, timeSlotDto));
	}
	
	@DeleteMapping("/{slotId}")
	public ResponseEntity<Void> deleteTimeSlotById(@PathVariable("slotId") Long slotId){
		logger.info("DELETE TIME SLOT BY ID API CALLED");
		
		timeSlotService.deleteSlotById(slotId);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("")
	public ResponseEntity<Void> deleteAllTimeSlots(){
		logger.info("DELETE ALL TIME SLOTS API CALLED");

		timeSlotService.deleteAllTimeSlots();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{slotId}/status")
	public ResponseEntity<TimeSlotDto> updateSlotStatus(
	        @PathVariable Long slotId,
	        @RequestParam SlotStatus status) {
		logger.info("UPDATE TIME SLOT STATUS API CALLED");

	    TimeSlotDto updatedSlot = timeSlotService.updateSlotStatus(slotId, status);

	    return ResponseEntity.ok(updatedSlot);
	}
	
	
	
}
