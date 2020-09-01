package com.kat.testcontainers.service;

import java.util.List;

import com.kat.testcontainers.model.Alien;

public interface AlienService {
	Alien create(Alien alien);
	List<Alien> getAliens();
	Alien getAlien(Long id);
}
