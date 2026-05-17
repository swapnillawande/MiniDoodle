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
    
    // helper function to create user with different email
    private UserDto createUser(String username) {

        UserDto userDto = new UserDto();

        long time = System.currentTimeMillis();

        userDto.setUsername(username);
        userDto.setEmail(username + time + "@test.com");

        return userService.addUser(userDto);
    }

    @Test
    public void testAddUser() {
    	
        UserDto savedUser = createUser("testuser");

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    public void testGetUserById() {

        UserDto savedUser = createUser("testuser");
        UserDto foundUser = userService.getUserById(savedUser.getId());

        assertNotNull(foundUser);
        
        assertEquals(savedUser.getId(), foundUser.getId());
        
        assertEquals(savedUser.getUsername(), foundUser.getUsername());
        
        assertEquals(savedUser.getEmail(), foundUser.getEmail());
        
    }
    
    @Test
    public void testGetAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        assertNotNull(users);
    }
    
    @Test
    public void testDeleteUserById() {
    	
    	
    	UserDto savedUser = createUser("testuser");    
             
        userService.deleteUserById(savedUser.getId());
        
        assertThrows(RuntimeException.class, () -> {
        			userService.getUserById(savedUser.getId());
        }); 	
        
        
    }
    
    @Test
    public void testUpdateUserById() {

    	UserDto savedUser = createUser("testuser");

        Long userId = savedUser.getId();

        UserDto user = userService.getUserById(userId);

        long time = System.currentTimeMillis();
        
        user.setUsername("leo");
        user.setEmail("leo"+time+"@test.com");

        UserDto updatedUser = userService.updateUserById(userId, user);

        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        
        
        
    }
    

    
    
    
}












