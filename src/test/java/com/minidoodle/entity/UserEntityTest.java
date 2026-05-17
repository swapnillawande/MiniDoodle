package com.minidoodle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserEntityTest {

	@Test
	public void testUserEntity() {
		
		AppUser appUser = new AppUser();
		
		appUser.setId(101L);
		appUser.setUsername("testuser");
		appUser.setEmail("testuser@test.com");
		
		assertNotNull(appUser);
		assertEquals(101L, appUser.getId());
		assertEquals("testuser", appUser.getUsername());
		assertEquals("testuser@test.com", appUser.getEmail());

		
		
	}
	
}
