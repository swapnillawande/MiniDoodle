package com.minidoodle.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.minidoodle.dto.UserDto;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;
 

    @Test
    public void testAddUser() {

        UserDto userDto = new UserDto();

        userDto.setUsername("candidate");
        userDto.setEmail("candidate@test.com");
        
        
        UserDto savedUser = userService.addUser(userDto);

        assertNotNull(savedUser);

        assertEquals("candidate", savedUser.getUsername());

        assertEquals("candidate@test.com", savedUser.getEmail());
    }

    @Test
    public void testGetUserById() {

        UserDto userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setEmail("user@test.com");

        UserDto savedUser = userService.addUser(userDto);
        UserDto foundUser = userService.getUserById(savedUser.getId());

        assertNotNull(foundUser);
        
        assertEquals(savedUser.getId(), foundUser.getId());
        
        assertEquals("user", foundUser.getUsername());
        
        assertEquals("user@test.com", foundUser.getEmail());
        
    }
    
    @Test
    public void testGetAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        assertNotNull(users);
    }
    
    @Test
    public void testDeleteUserById() {
    	
    	
        UserDto userDto = new UserDto();

        userDto.setUsername("sample");
        userDto.setEmail("sample@test.com");
        
        
        UserDto savedUser = userService.addUser(userDto);
        
        
        
        userService.deleteUserById(savedUser.getId());
        
        assertThrows(RuntimeException.class, () -> {
        			userService.getUserById(savedUser.getId());
        }); 	
        
        
    }
    
    @Test
    public void testUpdateUserById() {

        UserDto userDto = new UserDto();

        userDto.setUsername("sample");
        userDto.setEmail("sample@test.com");
        

        UserDto savedUser = userService.addUser(userDto);

        Long userId = savedUser.getId();

        UserDto user = userService.getUserById(userId);

        user.setUsername("leo");
        user.setEmail("leo@test.com");

        UserDto updatedUser = userService.updateUserById(userId, user);

        assertEquals("leo", updatedUser.getUsername());
        assertEquals("leo@test.com", updatedUser.getEmail());
        
        
        
    }
    
    
    
    
    
    
}












