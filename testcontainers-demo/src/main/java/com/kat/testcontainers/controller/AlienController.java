package com.kat.testcontainers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kat.testcontainers.model.Alien;
import com.kat.testcontainers.service.AlienService;

@RestController
public class AlienController {
	
	private AlienService alienService;

	@Autowired
	public AlienController(AlienService alienService) {
		this.alienService = alienService;
	}
	
	@PostMapping("/aliens")
	public ResponseEntity<Alien> createAlien(@RequestBody Alien alien) {
		Alien created = alienService.create(alien);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/aliens")
	public ResponseEntity<List<Alien>> getAliens() {
		return new ResponseEntity<>(alienService.getAliens(), HttpStatus.OK);
	}
	
	@GetMapping("/aliens/{id}")
	public ResponseEntity<Alien> getAlien(@PathVariable long id) {
		return new ResponseEntity<>(alienService.getAlien(id), HttpStatus.OK);
	}

}
