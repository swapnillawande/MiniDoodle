package com.minidoodle.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidoodle.entity.TimeSlot;
import com.minidoodle.entity.enums.SlotStatus;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long>{

    List<TimeSlot> findByOwnerId(Long ownerId);

    List<TimeSlot> findByOwnerIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long ownerId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
    
    List<TimeSlot> findByStatus(SlotStatus status);
}
