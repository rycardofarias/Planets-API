package com.example.planetsAPI.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.planetsAPI.entities.Planet;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long>{

	Optional<Planet> findByName(String name);
}
