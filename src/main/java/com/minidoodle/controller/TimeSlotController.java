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

@RestController
@RequestMapping("/time-slots")
public class TimeSlotController {

	@Autowired
	private TimeSlotService timeSlotService;
	
	@PostMapping("")
	public ResponseEntity<TimeSlotDto> addTimeSlot(@RequestBody TimeSlotDto timeSlotDto){
		
		TimeSlotDto addedTimeSlot = timeSlotService.addSlot(timeSlotDto);
		
		return ResponseEntity.ok(addedTimeSlot);
	}
	
	@GetMapping("")
	public ResponseEntity<List<TimeSlotDto>> getAllTimeSlot(){
		
		List<TimeSlotDto> allSlots = timeSlotService.getAllSlots();
		return ResponseEntity.ok(allSlots);
		
	}
	
	@GetMapping("/{slotId}")
	public ResponseEntity<TimeSlotDto> getTimeSlotById(@PathVariable("slotId") Long slotId){
		
		return ResponseEntity.ok(timeSlotService.getSlotById(slotId));
	}
	
	
	@PutMapping("/{slotId}")
	public ResponseEntity<TimeSlotDto> updateTimeSlotById(
			@PathVariable("slotId") Long slotId,
			@RequestBody TimeSlotDto timeSlotDto
			){
		
		return ResponseEntity.ok(timeSlotService.updateSlotById(slotId, timeSlotDto));
	}
	
	@DeleteMapping("/{slotId}")
	public ResponseEntity<Void> deleteTimeSlotById(@PathVariable("slotId") Long slotId){
		
		timeSlotService.deleteSlotById(slotId);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("")
	public ResponseEntity<Void> deleteAllTimeSlots(){
		
		timeSlotService.deleteAllTimeSlots();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{slotId}/status")
	public ResponseEntity<TimeSlotDto> updateSlotStatus(
	        @PathVariable Long slotId,
	        @RequestParam SlotStatus status) {

	    TimeSlotDto updatedSlot =
	            timeSlotService.updateSlotStatus(slotId, status);

	    return ResponseEntity.ok(updatedSlot);
	}
	
	
	
}
