package com.example.planetsAPI.services;

import static common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.planetsAPI.entities.Planet;
import com.example.planetsAPI.repositories.PlanetRepository;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

	@InjectMocks
	private PlanetService planetService;
	
	@Mock
	private PlanetRepository planetRepository;
	
	@Test
	public void createPlanet_WithValidData_ReturnsPlanet() {
		
		when(planetRepository.save(PLANET)).thenReturn(PLANET);
		
		Planet planet = planetService.create(PLANET);
		
		assertThat(planet).isEqualTo(PLANET);
	}
}
