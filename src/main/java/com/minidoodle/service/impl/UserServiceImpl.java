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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

}
