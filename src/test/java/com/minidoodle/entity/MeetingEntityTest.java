package com.minidoodle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MeetingEntityTest {

    @Test
    public void testMeetingEntity() {

        Meeting meeting = new Meeting();

        meeting.setId(1L);
        meeting.setTitle("Full Stack Developer Interview");
        meeting.setDescription("Interview for java fullstack developer");
        meeting.setStartTime(LocalDateTime.of(2026, 5, 20, 10, 0));
        meeting.setEndTime(LocalDateTime.of(2026, 5, 20, 11, 0));

        
        
        assertNotNull(meeting);

        assertEquals(1L, meeting.getId());
        
        assertEquals("Full Stack Developer Interview", meeting.getTitle());
        
        assertEquals("Interview for java fullstack developer", meeting.getDescription());
    }
	
}
