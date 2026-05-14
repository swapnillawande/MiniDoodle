package com.minidoodle.service;

import java.util.List;

import com.minidoodle.dto.UserDto;
import com.minidoodle.entity.AppUser;

public interface UserService {

	public List<UserDto> getAllUsers();
	
	public UserDto getUserById(Long userId);
	
	public UserDto addUser(UserDto userDto);
	
	public void deleteUserById(Long userId);
	
	public void deleteAllUsers();
}
