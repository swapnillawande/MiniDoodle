package com.minidoodle.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minidoodle.dto.UserDto;
import com.minidoodle.entity.AppUser;
import com.minidoodle.repository.UserRepository;
import com.minidoodle.service.UserService;
import com.minidoodle.service.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserDto> getAllUsers() {

	    List<AppUser> users = userRepository.findAll();

	    return users.stream()
	            .map(user -> modelMapper.map(user, UserDto.class))
	            .toList();
	}

	@Override
	public UserDto getUserById(Long userId) {
		AppUser user = userRepository.findById(userId).orElse(null);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		
		AppUser user = new AppUser();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		
		AppUser savedUser = userRepository.save(user);
		
		return modelMapper.map(savedUser, UserDto.class);	
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();		
	}

	@Override
	public UserDto updateUserById(Long userId, UserDto user) {

	    AppUser appUser = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

	    appUser.setUsername(user.getUsername());
	    appUser.setEmail(user.getEmail());

	    AppUser savedUser = userRepository.save(appUser);

	    return modelMapper.map(savedUser, UserDto.class);
	}

}
