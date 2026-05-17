package com.minidoodle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minidoodle.dto.UserDto;
import com.minidoodle.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		logger.info("GET ALL USERS API CALLED");
	    return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
		logger.info("GET USER BY ID API CALLED");
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@PostMapping("/add-user")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		logger.info("POST USER API CALLED");
		return ResponseEntity.ok(userService.addUser(userDto));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUserById(@PathVariable("userId")  Long userId) {
		logger.info("DELETE USER BY ID API CALLED");
	    userService.deleteUserById(userId);
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("")
	public ResponseEntity<Void> deleteAllUsers(){
		logger.info("DELETE ALL USERS API CALLED");
		userService.deleteAllUsers();
	    return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUserById(@PathVariable("userId") Long userId, @RequestBody UserDto userDto){
		logger.info("UPDATE USER BY ID API CALLED");
		
		UserDto updatedUserById = userService.updateUserById(userId, userDto);
		
	    return ResponseEntity.status(201).body(updatedUserById);
	}
	
	
	
	
}
