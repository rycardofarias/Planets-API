package com.example.planetsAPI.services;

import static common.PlanetConstants.INVALID_PLANET;
import static common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
	
	@Test
	public void createPlanet_withInvalidData_ThrowsException() {
		
		when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);
		
		assertThatThrownBy (()-> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void getPlanet_ByExistingId_ReturnsEmpty() {
		
		when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));
		
		Optional<Planet> planet = planetService.findById(1L);
		
		assertThat(planet).isNotEmpty();
		assertThat(planet.get()).isEqualTo(PLANET);
	}
	
	@Test
	public void getPlanet_ByUnexistingId_ReturnsEmpty() {
		
		when(planetRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Optional<Planet> planet = planetService.findById(1L);
		
		assertThat(planet).isEmpty();
	
	}
}
