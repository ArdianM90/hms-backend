package com.project.hms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(properties = "spring.profiles.active=test")
class HotelManagementSystemApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void encoder() {
		System.out.println("PASSWORD: " + passwordEncoder.encode("demo123"));
	}

}
