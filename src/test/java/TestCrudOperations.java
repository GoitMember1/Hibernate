import org.example.model.Client;
import org.example.model.Planet;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCrudOperations {

    private ClientCrudService clientService;
    private PlanetCrudService planetService;

    @BeforeAll
    public void setup() {
        clientService = new ClientCrudService();
        planetService = new PlanetCrudService();
    }

    @BeforeEach
    public void prepareDatabase() {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "").load();
        flyway.clean();
        flyway.migrate();

    }

    @Test
    public void testCreateAndRetrieveClient() {
        Client client = new Client();
        client.setName("Test Client");
        clientService.createClient(client);

        Client retrievedClient = clientService.getClient(client.getId());
        assertNotNull(retrievedClient);
        assertEquals("Test Client", retrievedClient.getName());
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client();
        client.setName("Test Client");
        clientService.createClient(client);

        client.setName("Updated Client");
        clientService.updateClient(client);

        Client retrievedClient = clientService.getClient(client.getId());
        assertNotNull(retrievedClient);
        assertEquals("Updated Client", retrievedClient.getName());
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client();
        client.setName("Test Client");
        clientService.createClient(client);

        clientService.deleteClient(client.getId());

        Client retrievedClient = clientService.getClient(client.getId());
        assertNull(retrievedClient);
    }

    @Test
    public void testCreateAndRetrievePlanet() {
        Planet planet = new Planet();
        planet.setId("MARS");
        planet.setName("Mars");
        planetService.createPlanet(planet);

        Planet retrievedPlanet = planetService.getPlanet(planet.getId());
        assertNotNull(retrievedPlanet);
        assertEquals("Mars", retrievedPlanet.getName());
    }

    @Test
    public void testInvalidPlanetId() {
        Planet planet = new Planet();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            planet.setId("mars123");
        });

        String expectedMessage = "Planet ID must consist of uppercase letters only.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdatePlanet() {
        Planet planet = new Planet();
        planet.setId("MARS");
        planet.setName("Mars");
        planetService.createPlanet(planet);

        planet.setName("Updated Mars");
        planetService.updatePlanet(planet);

        Planet retrievedPlanet = planetService.getPlanet(planet.getId());
        assertNotNull(retrievedPlanet);
        assertEquals("Updated Mars", retrievedPlanet.getName());
    }

    @Test
    public void testDeletePlanet() {
        Planet planet = new Planet();
        planet.setId("MARS");
        planet.setName("Mars");
        planetService.createPlanet(planet);

        planetService.deletePlanet(planet.getId());

        Planet retrievedPlanet = planetService.getPlanet(planet.getId());
        assertNull(retrievedPlanet);
    }
}