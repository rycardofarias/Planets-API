import static com.example.planetsAPI.common.PlanetConstants.PLANET;
import static com.example.planetsAPI.common.PlanetConstants.TATOOINE;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.example.planetsAPI.PlanetsApiApplication;
import com.example.planetsAPI.entities.Planet;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = PlanetsApiApplication.class)
@Sql(scripts = {"/import_planets.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/remove_planets.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class PlanetIT {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void createPlanet_ReturnsCreadted() {
		
		ResponseEntity<Planet> planet = restTemplate.postForEntity("/planets", PLANET, Planet.class);
	
		assertThat(planet.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(planet.getBody().getId()).isNotNull();
		assertThat(planet.getBody().getName()).isEqualTo(PLANET.getName());
		assertThat(planet.getBody().getClimate()).isEqualTo(PLANET.getClimate());
		assertThat(planet.getBody().getTerrain()).isEqualTo(PLANET.getTerrain());
		
	}
	
	@Test
	public void getPlanet_ReturnsPlanet() {
		
		ResponseEntity<Planet> planet = restTemplate.getForEntity("/planets/1", Planet.class);
	
		assertThat(planet.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(planet.getBody()).isEqualTo(TATOOINE);

	}
}
