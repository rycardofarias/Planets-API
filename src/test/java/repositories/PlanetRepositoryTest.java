package repositories;

import static common.PlanetConstants.PLANET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.example.planetsAPI.PlanetsApiApplication;
import com.example.planetsAPI.entities.Planet;
import com.example.planetsAPI.repositories.PlanetRepository;;

@DataJpaTest
@ContextConfiguration(classes = {PlanetsApiApplication.class})
public class PlanetRepositoryTest {

	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
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
}
