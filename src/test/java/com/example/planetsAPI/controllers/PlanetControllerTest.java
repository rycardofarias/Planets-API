package com.example.planetsAPI.controllers;

import static com.example.planetsAPI.common.PlanetConstants.PLANET;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.planetsAPI.entities.Planet;
import com.example.planetsAPI.services.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PlanetService planetService;
	
	@Test
	public void createPlanet_WithValidData_ReturnsCreated() throws Exception {
		
		when(planetService.create(PLANET)).thenReturn(PLANET);
		
		mockMvc.perform(post("/planets")
			.content(objectMapper.writeValueAsString(PLANET))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").value(PLANET));
	}
	
	@Test
	public void createPlanet_WithInvalidData_ReturnsCreated() throws Exception {
		
		Planet emptyPlanet = new Planet();
		Planet invalidPlanet = new Planet("", "", "");
		
		mockMvc.perform(post("/planets")
				.content(objectMapper.writeValueAsString(emptyPlanet))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnprocessableEntity());
		
		mockMvc.perform(post("/planets")
				.content(objectMapper.writeValueAsString(invalidPlanet))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void createPlanet_WithExistingName_ReturnsCreated() throws Exception {
	
		when(planetService.create(any())).thenThrow(DataIntegrityViolationException.class);
		
		mockMvc.perform(post("/planets")
				.content(objectMapper.writeValueAsString(PLANET))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void getPlanet_ByExistingId_ReturnPlanet() throws Exception {
		
		when(planetService.findById(1L)).thenReturn(Optional.of(PLANET));
		
		mockMvc.perform(get("/planets/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(PLANET));
	}
	
	@Test
	public void getPlanet_ByUnexistingId_ReturnsNotFound() throws Exception {
				
		mockMvc.perform(get("/planets/1"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getPlanet_ByExistingName_ReturnsPlanet() throws Exception {

		when(planetService.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));
		
		mockMvc.perform(get("/planets/name/" + PLANET.getName()))
				.andExpect(status().isOk())  
				.andExpect(jsonPath("$").value(PLANET));
	}
	
	@Test
	public void getPlanet_ByUnexistingName_ReturnsNotFound() throws Exception {
		mockMvc.perform(get("/planets/name"))
		.andExpect(status().isNotFound());
	}
}
