package com.minidoodle.service;

import java.time.LocalDateTime;
import java.util.List;

import com.minidoodle.entity.TimeSlot;

public interface TimeSlotService {

    List<TimeSlot> findByOwnerId(Long ownerId);

    List<TimeSlot> findByOwnerIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long ownerId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
}
