package com.minidoodle.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.minidoodle.entity.TimeSlot;
import com.minidoodle.service.TimeSlotService;

public class TimeSlotServiceImpl implements TimeSlotService{

	@Override
	public List<TimeSlot> findByOwnerId(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimeSlot> findByOwnerIdAndStartTimeLessThanAndEndTimeGreaterThan(Long ownerId, LocalDateTime endTime,
			LocalDateTime startTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
