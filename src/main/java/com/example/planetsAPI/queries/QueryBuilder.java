package com.example.planetsAPI.queries;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.example.planetsAPI.entities.Planet;

public class QueryBuilder {

	private QueryBuilder() {
		
	}
	
	public static Example<Planet> makeQuery(Planet planet) {
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
				.withIgnoreCase().withIgnoreNullValues();
		
		return Example.of(planet, exampleMatcher);
	}
}
