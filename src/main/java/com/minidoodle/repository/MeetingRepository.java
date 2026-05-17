package com.minidoodle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidoodle.entity.AppUser;
import com.minidoodle.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{

	List<Meeting> findByOrganizer(AppUser organizer);
}
