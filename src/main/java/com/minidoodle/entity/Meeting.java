package com.minidoodle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    private AppUser organizer;

    @ManyToMany
    private List<AppUser> participants;

	public Meeting() {

	}

	public Meeting(Long id, String title, String description, LocalDateTime startTime, LocalDateTime endTime,
			AppUser organizer, List<AppUser> participants) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organizer = organizer;
		this.participants = participants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public AppUser getOrganizer() {
		return organizer;
	}

	public void setOrganizer(AppUser organizer) {
		this.organizer = organizer;
	}

	public List<AppUser> getParticipants() {
		return participants;
	}

	public void setParticipants(List<AppUser> participants) {
		this.participants = participants;
	}
    
    
    
}