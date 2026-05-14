package com.minidoodle.service;

import java.util.List;

import com.minidoodle.entity.AppUser;

public interface UserService {

	public List<AppUser> getAllUsers();
	
	public AppUser getUserById(Long userId);
	
	public AppUser addUser();
	
	public void deleteUserById(Long userId);
	
	public void deleteAllUsers();
}
