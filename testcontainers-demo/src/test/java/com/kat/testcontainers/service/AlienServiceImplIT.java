package com.kat.testcontainers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.kat.testcontainers.model.Alien;

@SpringBootTest
@Testcontainers
public class AlienServiceImplIT {
	
	@Autowired
	private AlienService alienService;
	
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
	 void testAlienCreationAndRetrieval() {
	 	// Given I create two aliens
		 Alien alien1 = Alien.builder()
				 .firstname("Mike")
				 .age(13)
				 .friendly(false)
				 .planet("Mars")
				 .build();
		 Alien alien2 = Alien.builder()
				 .firstname("George")
				 .age(11)
				 .friendly(true)
				 .planet("Jupiter")
				 .build();

	     Alien created1 = alienService.create(alien1);
		 alienService.create(alien2);

		 // When
		 List<Alien> aliens = alienService.getAliens();
		 Alien retrieved1 = alienService.getAlien(created1.getId());

	     // Then
	     assertNotNull(aliens);
	     assertEquals(2, aliens.size());

		 assertEquals(created1.getFirstname(), retrieved1.getFirstname());
		 assertEquals(created1.isFriendly(), retrieved1.isFriendly());
		 assertEquals(created1.getPlanet(), retrieved1.getPlanet());
	     assertEquals(created1.getAge(), retrieved1.getAge());
	 }
}
