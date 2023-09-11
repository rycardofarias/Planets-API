# Planet API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) 

## Project Description

The Planet API is a Spring Boot application.

## Technologies Used


- Spring Boot 3.1.3
- PostgreSQL
- JUnit 5
- Mockito
- AssertJ
- Hamcrest
- JaCoCo 0.8.10
- PITest 1.14.4

## Installation

Follow these steps to set up and run the Planet API in your local environment:

1. Clone the repository: git clone https://github.com/rycardofarias/Planets-API.git
2. Configure a PostgreSQL database and update the connection information in the `application.properties` file.
3. Install dependencies: mvn install
4. Run the application: mvn spring-boot:run

## Endepoints

- **Method:** POST : create
- **Endpoint:** `/planets`
- **Description:** Create a new planet.
- **Example Request:** (JSON)
```json
{
   "name": "Tatooine",
   "climate": "Arid",
   "terrain": "Desert"
}
```
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


## Testing
To run unit and integration tests, use the following command:
mvn test

- The PlanetServiceTest class contains unit tests for the PlanetService class, which is responsible for providing services related to planets.
- The PlanetControllerTest class contains integration tests for the PlanetController class, which handles API requests related to planets.
- The PlanetRepositoryTest class contains tests for the PlanetRepository, which interacts with the database for planet-related operations.
- The PlanetIT class comprises subcutaneous tests for the Planet API.

### Test Coverage
To generate a test coverage report with JaCoCo, use the following command: mvn clean test jacoco:report

You can view the coverage report at target/site/jacoco/index.html.

To generate mutation test coverage using PITest, run the following command: mvn test-compile org.pitest:pitest-maven:mutationCoverage

You can view the coverage using PITest at target/pit-reports/index.html.

