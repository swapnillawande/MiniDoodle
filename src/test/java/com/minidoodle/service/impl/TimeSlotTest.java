package com.minidoodle.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.minidoodle.dto.TimeSlotDto;
import com.minidoodle.dto.UserDto;
import com.minidoodle.entity.enums.SlotStatus;
import com.minidoodle.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
public class TimeSlotTest {

	@Autowired
	private UserService userService ;
	
	@Autowired
	private TimeSlotServiceImpl timeSlotServiceImpl;
	
	@Test
	public void testAddTimeSlot() {
		
		UserDto userDto = new UserDto();
		
		userDto.setUsername("owner");
		userDto.setEmail("owner@gmail.com");
		
		UserDto savedUser = userService.addUser(userDto);
		
		TimeSlotDto timeSlotDto = new TimeSlotDto();
		
	    timeSlotDto.setStartTime(LocalDateTime.of(2026, 5, 26, 10, 0));

	    timeSlotDto.setEndTime(LocalDateTime.of(2026, 5, 26, 11, 0));
	    
	    
	    timeSlotDto.setOwnerId(savedUser.getId());
	    
	    
	    TimeSlotDto addedSlot = timeSlotServiceImpl.addSlot(timeSlotDto);
	    
	    assertNotNull(addedSlot);
	    assertEquals(savedUser.getId(), addedSlot.getOwnerId());
	    assertEquals(SlotStatus.FREE, addedSlot.getStatus());
	    
		
		
	}
	
	
	
    
    @Test
    public void testGetAllTimeSlots() {
    	
    	
    	List<TimeSlotDto> allAvailableTimeSlots = timeSlotServiceImpl.getAllAvailableTimeSlots();
    	
    	assertNotNull(allAvailableTimeSlots);
    }
	

	
	
	
}













