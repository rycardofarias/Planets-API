package com.example.planetsAPI.repositories;

import static com.example.planetsAPI.common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.example.planetsAPI.PlanetsApiApplication;
import com.example.planetsAPI.entities.Planet;;

@DataJpaTest
@ContextConfiguration(classes = {PlanetsApiApplication.class})
public class PlanetRepositoryTest {

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@AfterEach
	public void afterEach() {
		PLANET.setId(null);
	}
	
	@Test
	public void createPlanet_WithValidData_ReturnsPlanet() {
		
		Planet planet = planetRepository.save(PLANET);
		
		planet = testEntityManager.find(Planet.class, planet.getId());
		
		assertThat(planet).isNotNull();
		assertThat(planet.getName()).isEqualTo(PLANET.getName());
		assertThat(planet.getClimate()).isEqualTo(PLANET.getClimate());
		assertThat(planet.getTerrain()).isEqualTo(PLANET.getTerrain());

	}
	
	@Test
	public void createPlanet_WithInvalidData_ThrowsException() {
		Planet emptyPlanet = new Planet();
		Planet invalidPlanet = new Planet("", "", "");
		
		assertThatThrownBy(() -> planetRepository.save(emptyPlanet));
		assertThatThrownBy(() -> planetRepository.save(invalidPlanet));
	}
	
	@Test
	public void createPlanet_WithExistingName_ThrowsException() {
		
		Planet planet = testEntityManager.persistFlushFind(PLANET);
		testEntityManager.detach(planet);
		planet.setId(null);
		
		assertThatThrownBy(() -> planetRepository.save(planet)).isInstanceOf(RuntimeException.class);		
	}
	
	@Test
	public void getPlanet_ByExistingId_ReturnPlanet() throws Exception {
		
		Planet planet = testEntityManager.persistFlushFind(PLANET);
		
		Optional<Planet> planetOpt = planetRepository.findById(planet.getId());
		
		assertThat(planetOpt).isNotEmpty();
		assertThat(planetOpt.get()).isEqualTo(planet);
	}
	
	@Test
	public void getPlanet_ByUnexstingId_ReturnEmpty() {
		
		Optional<Planet> planetOpt = planetRepository.findById(1L);
		
		assertThat(planetOpt).isEmpty();
	}
	
	@Test
	public void getPlanet_ByExistingName_ReturnsPlanet() throws Exception {

		Planet planet = testEntityManager.persistFlushFind(PLANET);
		
		Optional<Planet> planetOpt = planetRepository.findByName(planet.getName());
		
		assertThat(planetOpt).isNotEmpty();
		assertThat(planetOpt.get()).isEqualTo(planet);
	}
	
	@Test
	public void getPlanet_ByUnexistingName_ReturnsNotFound() throws Exception {
		
		Optional<Planet> planetOpt = planetRepository.findByName("name");
		
		assertThat(planetOpt).isEmpty();
	}
	
	
}
