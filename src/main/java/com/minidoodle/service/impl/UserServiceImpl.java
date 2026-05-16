package com.minidoodle.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minidoodle.dto.UserDto;
import com.minidoodle.entity.AppUser;
import com.minidoodle.repository.UserRepository;
import com.minidoodle.service.UserService;
import com.minidoodle.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserDto> getAllUsers() {

	    logger.info("Getting all users..");
		
	    List<AppUser> users = userRepository.findAll();

	    return users.stream()
	            .map(user -> modelMapper.map(user, UserDto.class))
	            .toList();
	}

	@Override
	public UserDto getUserById(Long userId) {
		
	    logger.info("Getting user with ID: "+ userId);
		
	    AppUser appUser = userRepository.findById(userId)
	            .orElseThrow(() -> {
	                logger.error("User not found with ID: "+ userId);
	                return new ResourceNotFoundException(
	                        "User not found with ID: " + userId);
	            });
	    
	    
		AppUser user = userRepository.findById(userId).orElse(null);
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		
	    logger.info("Adding new user with email: "+ userDto.getEmail());
		
		AppUser user = new AppUser();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		
		AppUser savedUser = userRepository.save(user);
		
	    logger.info("User added successfully with ID: "+ savedUser.getId());

		
		return modelMapper.map(savedUser, UserDto.class);	
	}

	@Override
	public void deleteUserById(Long userId) {
		
		
	    AppUser appUser = userRepository.findById(userId)
	            .orElseThrow(() -> {
	                logger.error("User not found with ID: "+ userId);
	                return new ResourceNotFoundException(
	                        "User not found with ID: " + userId);
	            });
		
		
	    logger.warn("Deleting user with ID: "+ userId);

		userRepository.deleteById(userId);
		
	}

	@Override
	public void deleteAllUsers() {
		
	    logger.warn("Deleting all users..");
		
		userRepository.deleteAll();		
	}

	@Override
	public UserDto updateUserById(Long userId, UserDto user) {

	    logger.info("Updating user with ID: "+ userId);
		
	    AppUser appUser = userRepository.findById(userId)
	            .orElseThrow(() -> {
	                logger.error("User not found with ID: "+ userId);
	                return new ResourceNotFoundException(
	                        "User not found with ID: " + userId);
	            });

	    appUser.setUsername(user.getUsername());
	    appUser.setEmail(user.getEmail());

	    AppUser savedUser = userRepository.save(appUser);

	    logger.info("User updated successfully with ID: "+ savedUser.getId());
	    
	    return modelMapper.map(savedUser, UserDto.class);
	}

}
