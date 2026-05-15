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

    private AppUser owner;

    private Meeting meeting;

	public TimeSlotDto() {

	}

	public TimeSlotDto(Long id, LocalDateTime startTime, LocalDateTime endTime, SlotStatus status, AppUser owner,
			Meeting meeting) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.owner = owner;
		this.meeting = meeting;
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

	public AppUser getOwner() {
		return owner;
	}

	public void setOwner(AppUser owner) {
		this.owner = owner;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
    
	
    
    
    
}
