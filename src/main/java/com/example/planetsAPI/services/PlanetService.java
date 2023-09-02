package com.example.planetsAPI.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.planetsAPI.entities.Planet;
import com.example.planetsAPI.repositories.PlanetRepository;

@Service
public class PlanetService {
 
	private PlanetRepository planetRepository;
	
	public PlanetService(PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}

	public Planet create(Planet planet) {
		return planetRepository.save(planet);
	}
	
	public Optional<Planet> findById(Long id) {
		return planetRepository.findById(id);
	}
	
	public Optional<Planet> findByName(String name) {
		return planetRepository.findByName(name);
	}
}
