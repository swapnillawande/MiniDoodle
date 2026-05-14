package com.minidoodle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		
		return ResponseEntity.ok(userService.addUser(userDto));
	}
	
	
}
