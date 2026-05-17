package com.minidoodle.service;

import java.util.List;

import com.minidoodle.dto.MeetingDto;

public interface MeetingService {

    List<MeetingDto> getAllMeetings();
    
    List<MeetingDto> getAllMeetingsByOrganizerId(Long organizerId);

    MeetingDto getMeetingById(Long meetingId);

    MeetingDto addMeeting(MeetingDto meetingDto);

    MeetingDto updateMeetingById(Long meetingId, MeetingDto meetingDto);
    
    MeetingDto bookSlot(Long slotId, MeetingDto meetingDto);

    void deleteMeetingById(Long meetingId);

    void deleteAllMeetings();
}
