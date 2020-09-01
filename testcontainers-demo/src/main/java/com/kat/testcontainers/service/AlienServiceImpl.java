package com.kat.testcontainers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kat.testcontainers.model.Alien;
import com.kat.testcontainers.repository.AlienRepository;

@Service
public class AlienServiceImpl implements AlienService {
	
	private AlienRepository alienRepository;
	
	@Autowired
	public AlienServiceImpl(AlienRepository alienRepository) {
		this.alienRepository = alienRepository;
	}

	@Override
	public Alien create(Alien alien) {
		return alienRepository.save(alien);
	}

	@Override
	public List<Alien> getAliens() {
		return alienRepository.findAll();
	}

	@Override
	public Alien getAlien(Long id) {
		return alienRepository.findById(id).orElse(null);
	}

}
