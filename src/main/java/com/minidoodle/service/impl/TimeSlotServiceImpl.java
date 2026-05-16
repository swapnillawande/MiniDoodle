package com.minidoodle.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.entity.AppUser;
import com.minidoodle.entity.Meeting;
import com.minidoodle.entity.TimeSlot;
import com.minidoodle.entity.enums.SlotStatus;
import com.minidoodle.exception.ResourceNotFoundException;
import com.minidoodle.repository.MeetingRepository;
import com.minidoodle.repository.TimeSlotRepository;
import com.minidoodle.repository.UserRepository;
import com.minidoodle.service.TimeSlotService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TimeSlotServiceImpl implements TimeSlotService{

	private static final Logger logger = LoggerFactory.getLogger(TimeSlotServiceImpl.class);	
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<TimeSlotDto> getAllSlots() {
		
		logger.info("Getting all time slots..");
		
		List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();
		
	    return allTimeSlots.stream()
	            .map(timeSlot -> modelMapper.map(timeSlot, TimeSlotDto.class))
	            .toList();
	}

	@Override
	public TimeSlotDto getSlotById(Long slotId) {
		
		logger.info("Getting slot with ID: "+ slotId);
		
		TimeSlot timeSlot = timeSlotRepository.findById(slotId)
	            .orElseThrow(() -> new ResourceNotFoundException("Slot not found with ID: " + slotId));

		
		return modelMapper.map(timeSlot, TimeSlotDto.class);
	}

	@Override
	public TimeSlotDto addSlot(TimeSlotDto timeSlotDto) {

	    logger.info("Creating new time slot");

	    if (timeSlotDto.getStartTime().isAfter(timeSlotDto.getEndTime())) {
	        logger.error("Start time cannot be after end time");
	        throw new RuntimeException("Start time cannot be after end time");
	    }
	    
	    AppUser owner = userRepository.findById(timeSlotDto.getOwnerId())
	            .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));


	    TimeSlot timeSlot = new TimeSlot();

	    timeSlot.setStartTime(timeSlotDto.getStartTime());
	    timeSlot.setEndTime(timeSlotDto.getEndTime());
	    
	    if (timeSlotDto.getStatus() != null) {
	        timeSlot.setStatus(timeSlotDto.getStatus());
	    } else {
	        timeSlot.setStatus(SlotStatus.FREE);
	    }
	    
	    timeSlot.setOwner(owner);
	    
	    if (timeSlotDto.getMeetingId() != null) {

	        Meeting meeting = meetingRepository.findById(timeSlotDto.getMeetingId())
	                .orElseThrow(() ->{
	                	logger.error("Meeting not found with ID: "+timeSlotDto.getMeetingId());
	                	return new ResourceNotFoundException("Meeting not found");
	                }
	                        );

	        timeSlot.setMeeting(meeting);
	    }

	    TimeSlot savedSlot = timeSlotRepository.save(timeSlot);

	    logger.info("Time slot created with ID: "+ savedSlot.getId());

	    return modelMapper.map(savedSlot, TimeSlotDto.class);
	}

	@Override
	public TimeSlotDto updateSlotById(Long slotId, TimeSlotDto timeSlotDto) {
		
		logger.info("Updating time slot with ID: "+ slotId);
		
		TimeSlot timeSlot = timeSlotRepository.findById(slotId)
	            .orElseThrow(() -> {
	                logger.error("Slot not found with ID: "+ slotId);
	                return new ResourceNotFoundException(
	                        "Slot not found with ID: " + slotId);
	            });

		timeSlot.setStartTime(timeSlotDto.getStartTime());
		timeSlot.setEndTime(timeSlotDto.getEndTime());
		timeSlot.setStatus(timeSlotDto.getStatus());
		
		AppUser owner = userRepository.findById(timeSlotDto.getOwnerId())
		        .orElseThrow(() -> {
	                logger.error("Owner not found with ID: "+ timeSlotDto.getOwnerId());
		        	return new ResourceNotFoundException("Owner not found");
		        });

		Meeting meeting = meetingRepository.findById(timeSlotDto.getMeetingId())
		        .orElseThrow(() -> {
	                logger.error("Meeting not found with ID: "+ timeSlotDto.getMeetingId());
		        	return new ResourceNotFoundException("Meeting not found");
		        });
		
		timeSlot.setOwner(owner);
		timeSlot.setMeeting(meeting);
		
		TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
		
	    logger.info("Slot updated successfully with ID: "+ savedTimeSlot.getId());
		
		return modelMapper.map(savedTimeSlot, TimeSlotDto.class);
	}

	@Override
	public void deleteSlotById(Long slotId) {
		
		logger.warn("Deleting slot with ID: "+ slotId);
		
	    TimeSlot timeSlot = timeSlotRepository.findById(slotId)
	            .orElseThrow(() -> {
	                logger.error("Time slot found with ID: "+ slotId);
	            	return new ResourceNotFoundException("Time slot not found with ID: " + slotId);
	            });

		timeSlotRepository.deleteById(slotId);
		
	}

	@Override
	public TimeSlotDto updateSlotStatus(Long slotId, SlotStatus status) {

		
	    TimeSlot timeSlot = timeSlotRepository.findById(slotId)
	            .orElseThrow(() -> {
	                logger.error("Time slot found with ID: "+ slotId);
	            	return new ResourceNotFoundException("Time slot not found with ID: " + slotId);
	            });

	    timeSlot.setStatus(status);

	    TimeSlot updatedSlot = timeSlotRepository.save(timeSlot);
	    
		logger.info("Updating slot status for slot ID: "+ slotId);
		
	    return modelMapper.map(updatedSlot, TimeSlotDto.class);
	}

	@Override
	public void deleteAllTimeSlots() {
		
		logger.warn("Deleting all time slots..");

		
		timeSlotRepository.deleteAll();
		
	}



}
