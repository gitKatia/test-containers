package com.kat.testcontainers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.kat.testcontainers.model.Alien;
import com.kat.testcontainers.service.AlienService;

@Testcontainers
@SpringBootTest
public class AlienControllerIT {
	
	@Autowired
	private AlienController alienController;
	
	@Container
    private MySQLContainer MY_SQL_CONTAINER = new MySQLContainer()
            .withDatabaseName("testdb")
            .withUsername("admin")
            .withPassword("password123");
	
	 @Test
	 void testDBs() {
	        assertTrue(MY_SQL_CONTAINER.isRunning());
	 }
	
	@Test
	public void createAlienTest() throws Exception {

	 	// Given I create one alien
		Alien alien = Alien.builder()
				.firstname("Daniel")
				.friendly(true)
				.age(8)
				.planet("Saturn")
				.build();
		
		ResponseEntity<Alien> response1 = alienController.createAlien(alien);
		Alien created = response1.getBody();

		// When
        ResponseEntity<Alien> response2 = alienController.getAlien(created.getId());
        
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals("Daniel", response2.getBody().getFirstname());
	}
}
