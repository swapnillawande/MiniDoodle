package com.minidoodle.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.minidoodle.dto.MeetingDto;
import com.minidoodle.dto.UserDto;

@SpringBootTest
@ActiveProfiles("test")
public class MeetingServiceTest {

    @Autowired
    private MeetingServiceImpl meetingService;

    @Autowired
    private UserServiceImpl userService;

    // helper function to create user dao
    private UserDto createUser() {

        UserDto userDto = new UserDto();

        long time = System.currentTimeMillis();

        userDto.setUsername("user" + time);
        userDto.setEmail("user" + time + "@test.com");

        return userService.addUser(userDto);
    }
    
    // helper function to create meeting dao
    private MeetingDto createMeetingDto(Long organizerId, Long participantId) {

        MeetingDto meetingDto = new MeetingDto();

        meetingDto.setTitle("Java backend interview");
        meetingDto.setDescription("Spring Boot technical interview");

        meetingDto.setStartTime(LocalDateTime.of(2026, 5, 28, 10, 0));
        meetingDto.setEndTime(LocalDateTime.of(2026, 5, 28, 11, 0));
        meetingDto.setOrganizerId(organizerId);

        meetingDto.setParticipantIds(List.of(participantId));

        return meetingDto;
    }
    
    
    @Test
    public void testAddMeeting() {

        UserDto organizer = createUser();

        UserDto participant = createUser();

        MeetingDto meetingDto = createMeetingDto(organizer.getId(), participant.getId());

        MeetingDto savedMeeting = meetingService.addMeeting(meetingDto);

        assertNotNull(savedMeeting);

        assertNotNull(savedMeeting.getId());

        assertEquals("Java backend interview", savedMeeting.getTitle());

        assertEquals(organizer.getId(), savedMeeting.getOrganizerId());
    }
    
    
    
	
}
