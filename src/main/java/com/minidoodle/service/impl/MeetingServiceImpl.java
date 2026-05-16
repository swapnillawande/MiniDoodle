package com.minidoodle.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minidoodle.dto.MeetingDto;
import com.minidoodle.entity.AppUser;
import com.minidoodle.entity.Meeting;
import com.minidoodle.exception.ResourceNotFoundException;
import com.minidoodle.repository.MeetingRepository;
import com.minidoodle.repository.UserRepository;
import com.minidoodle.service.MeetingService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MeetingServiceImpl implements MeetingService{

	private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);	
	
	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<MeetingDto> getAllMeetings() {

	    logger.info("Getting all meetings..");

	    List<Meeting> allMeetings = meetingRepository.findAll();

	    return allMeetings.stream().map(this::convertToDto).toList();
	}

	@Override
	public MeetingDto getMeetingById(Long meetingId) {

	    logger.info("Getting meeting with ID: "+ meetingId);

	    Meeting meeting = meetingRepository.findById(meetingId)
	            .orElseThrow(() -> {
	                logger.warn("Meeting not found with ID: "+ meetingId);
	                return new ResourceNotFoundException("Meeting not found with ID: " + meetingId);
	            });

	    return convertToDto(meeting);
	}

	@Override
	public MeetingDto addMeeting(MeetingDto meetingDto) {

	    logger.info("Creating new meeting: "+meetingDto.getTitle());

	    Meeting meeting = new Meeting();

	    meeting.setTitle(meetingDto.getTitle());
	    meeting.setDescription(meetingDto.getDescription());
	    meeting.setStartTime(meetingDto.getStartTime());
	    meeting.setEndTime(meetingDto.getEndTime());


	    AppUser organizer = userRepository
	            .findById(meetingDto.getOrganizerId())
	            .orElseThrow(() -> {
	        	    logger.warn("Organizer not found with ID: "+meetingDto.getOrganizerId());

	            	return new ResourceNotFoundException("Organizer not found");
	            });

	    meeting.setOrganizer(organizer);


	    List<AppUser> participants =
	            meetingDto.getParticipantIds().stream()
	            .map(userId ->
	                    userRepository.findById(userId)
	                    .orElseThrow(() ->{
	    	        	    logger.warn("Participant not found with ID: "+userId);
	    	        	    
	                    	return new ResourceNotFoundException("Participant not found");
	                    }))
	            .collect(Collectors.toList());

	    meeting.setParticipants(participants);

	    Meeting savedMeeting = meetingRepository.save(meeting);

	    logger.info("Meeting added with ID: "+savedMeeting.getId());
	    
	    return convertToDto(savedMeeting);
	}

	@Override
	public MeetingDto updateMeetingById(Long meetingId, MeetingDto meetingDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMeetingById(Long meetingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllMeetings() {
		// TODO Auto-generated method stub
		
	}
	
	// helper funtion
	private MeetingDto convertToDto(Meeting meeting) {
	    MeetingDto dto = new MeetingDto();

	    dto.setId(meeting.getId());
	    dto.setTitle(meeting.getTitle());
	    dto.setDescription(meeting.getDescription());
	    dto.setStartTime(meeting.getStartTime());
	    dto.setEndTime(meeting.getEndTime());

	    if (meeting.getOrganizer() != null) {
	        dto.setOrganizerId(meeting.getOrganizer().getId());
	    }

	    if (meeting.getParticipants() != null) {
	        dto.setParticipantIds(
	                meeting.getParticipants().stream().map(AppUser::getId).toList()
	        );
	    }

	    return dto;
	}

}
