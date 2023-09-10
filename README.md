# Planet API

## Technologies used

- Spring Boot 3.1.3
- PostgreSQL
- JUnit 5
- Mockito
- AssertJ
- Hamcrest
- JaCoCo 0.8.10
- PITest 1.14.4

## Endepoints

### Create Planet
- **Method:** POST : create
- **Endpoint:** `/planets`
- **Description:** Creates a new planet.
- **Response:** Planet created successfully (status 201).

### Find Planet by ID
- **Method:** GET : findById
- **Endpoint:** `/planets/{id}`
- **Description:** Retrieves a planet by its ID.
- **Response:** Returns the found planet (status 200) or "Not Found" (status 404).

### Find Planet by Name
- **Method:** GET : findByName
- **Endpoint:** `/planets/name/{name}`
- **Description:** Retrieves a planet by its name.
- **Response:** Returns the found planet (status 200) or "Not Found" (status 404).

### List All Planets
- **Method:** GET : list
- **Endpoint:** `/planets`
- **Description:** Lists all planets, with an option to filter by terrain and climate.
- **Response:** Returns a list of planets (status 200).

### Delete Planet by ID
- **Method:** DELETE : delete
- **Endpoint:** `/planets/{id}`
- **Description:** Deletes a planet by its ID.
- **Response:** No content (status 204) after successful deletion.

## Test

- The PlanetServiceTest class contains unit tests for the PlanetService class, which is responsible for providing services related to planets.
- The PlanetControllerTest class contains integration tests for the PlanetController class, which handles API requests related to planets.
- The PlanetRepositoryTest class contains tests for the PlanetRepository, which interacts with the database for planet-related operations.
- The PlanetIT class comprises subcutaneous tests for the Planet API, evaluating the API's behavior in realistic end-to-end scenarios.
