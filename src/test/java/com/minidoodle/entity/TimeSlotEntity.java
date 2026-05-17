package com.minidoodle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.minidoodle.entity.enums.SlotStatus;

@SpringBootTest
@ActiveProfiles("test")
public class TimeSlotEntity {

	
	@Test
	public void testTimeSlotEntity() {
		
        TimeSlot timeSlot = new TimeSlot();

        timeSlot.setId(101L);
        timeSlot.setStartTime(LocalDateTime.of(2026, 5, 28, 14, 0));
        timeSlot.setEndTime(LocalDateTime.of(2026, 5, 28, 15, 0));
        timeSlot.setStatus(SlotStatus.FREE);

        
        
        assertNotNull(timeSlot);

        assertEquals(101L, timeSlot.getId());
        
        assertEquals(SlotStatus.FREE, timeSlot.getStatus());
		
	}
}
