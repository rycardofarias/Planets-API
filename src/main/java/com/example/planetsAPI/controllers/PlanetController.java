package com.example.planetsAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.planetsAPI.entities.Planet;
import com.example.planetsAPI.services.PlanetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;
	
	@PostMapping
	public ResponseEntity<Planet> create(@RequestBody @Valid Planet planet) {
		Planet planetCreted = planetService.create(planet);
		return ResponseEntity.status(HttpStatus.CREATED).body(planetCreted);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Planet> findById(@PathVariable("id") Long id){
		return planetService.findById(id).map(planet -> ResponseEntity.ok(planet))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<Planet> findByName(@PathVariable("name") String name){
		return planetService.findByName(name).map(planet -> ResponseEntity.ok(planet))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<Planet>> list(
			@RequestParam(required = false) String terrain,
			@RequestParam(required = false) String climate){
		
		List<Planet> planets = planetService.findAll(terrain, climate);
		return ResponseEntity.ok(planets);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Planet> delete(@PathVariable("id") Long id){
		planetService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
