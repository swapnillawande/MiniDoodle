package com.minidoodle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minidoodle.dto.MeetingDto;
import com.minidoodle.service.MeetingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("meetings")
public class MeetingController {

	private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);	
	
	@Autowired
	private MeetingService meetingService;
	
	
	@GetMapping("")
	public ResponseEntity<List<MeetingDto>> getAllMeetings(){
		logger.info("GET ALL MEETING API CALLED");
		
		return ResponseEntity.ok(meetingService.getAllMeetings());
	}
	
	@GetMapping("/{meetingId}")
	public ResponseEntity<MeetingDto> getMeetingById(@PathVariable("meetingId") Long meetingId){
		logger.info("GET MEETING BY ID API CALLED");
		
		return ResponseEntity.ok(meetingService.getMeetingById(meetingId));


	}
	
	
	@PostMapping("/addMeeting")
	public ResponseEntity<MeetingDto> addMeeting(@RequestBody MeetingDto meetingDto){
		logger.info("POST MEETING API CALLED");

		return ResponseEntity.ok(meetingService.addMeeting(meetingDto));
	}
	
	
}
