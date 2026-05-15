package com.minidoodle.dto;

import java.time.LocalDateTime;

import com.minidoodle.entity.AppUser;
import com.minidoodle.entity.Meeting;
import com.minidoodle.entity.enums.SlotStatus;



public class TimeSlotDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private SlotStatus status;

    private Long ownerId;
    
    private Long meetingId;

	public TimeSlotDto() {

	}

	public TimeSlotDto(Long id, LocalDateTime startTime, LocalDateTime endTime, SlotStatus status, Long ownerId,
			Long meetingId) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.ownerId = ownerId;
		this.meetingId = meetingId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public SlotStatus getStatus() {
		return status;
	}

	public void setStatus(SlotStatus status) {
		this.status = status;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
    
	
    
    
    
}
