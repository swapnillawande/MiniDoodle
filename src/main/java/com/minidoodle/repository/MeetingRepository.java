package com.minidoodle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidoodle.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long>{

}
