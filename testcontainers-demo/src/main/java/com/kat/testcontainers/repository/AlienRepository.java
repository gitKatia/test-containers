package com.kat.testcontainers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kat.testcontainers.model.Alien;

public interface AlienRepository extends JpaRepository<Alien,Long> {

}
