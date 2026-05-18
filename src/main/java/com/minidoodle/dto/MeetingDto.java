package com.minidoodle.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MeetingDto {

    private Long id;
    
    @NotBlank(message = "Meeting title is required")
    private String title;

    @NotBlank(message = "Meeting description is required")
    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    
    @NotNull(message = "Organizer id is required")
    private Long organizerId;

    @NotEmpty(message = "At least one participant is required")
    private List<Long> participantIds;

    public MeetingDto() {

    }

    public MeetingDto(Long id, String title, String description,
                      LocalDateTime startTime,
                      LocalDateTime endTime,
                      Long organizerId,
                      List<Long> participantIds) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizerId = organizerId;
        this.participantIds = participantIds;
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

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public List<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Long> participantIds) {
        this.participantIds = participantIds;
    }
    
    
}