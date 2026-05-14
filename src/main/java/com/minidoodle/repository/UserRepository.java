package com.minidoodle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minidoodle.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{

}
