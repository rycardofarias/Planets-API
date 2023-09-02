package com.example.planetsAPI.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.planetsAPI.entities.Planet;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long>{

}
