package com.minidoodle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minidoodle.dto.UserDto;
import com.minidoodle.service.UserService;


@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<UserDto>> getAllUsers() {

	    return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@PostMapping("")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		
		return ResponseEntity.ok(userService.addUser(userDto));
	}
	
	
	
}
