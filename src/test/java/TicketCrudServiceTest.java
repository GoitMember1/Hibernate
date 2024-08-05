import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TicketCrudServiceTest {

    private TicketCrudService ticketService;
    private ClientCrudService clientService;
    private PlanetCrudService planetService;

    @BeforeAll
    public void setup() {
        ticketService = new TicketCrudService();
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
    public void testCreateAndRetrieveTicket() {
        Client client = new Client();
        client.setName("Test Client");
        clientService.createClient(client);

        Planet fromPlanet = new Planet();
        fromPlanet.setId("MARS");
        fromPlanet.setName("Mars");
        planetService.createPlanet(fromPlanet);

        Planet toPlanet = new Planet();
        toPlanet.setId("VEN");
        toPlanet.setName("Venus");
        planetService.createPlanet(toPlanet);

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticketService.createTicket(ticket);

        Ticket retrievedTicket = ticketService.getTicket(ticket.getId());
        assertNotNull(retrievedTicket);
        assertEquals(client, retrievedTicket.getClient());
        assertEquals(fromPlanet, retrievedTicket.getFromPlanet());
        assertEquals(toPlanet, retrievedTicket.getToPlanet());
    }

    @Test
    public void testCreateTicketWithInvalidClient() {
        Planet fromPlanet = new Planet();
        fromPlanet.setId("MARS");
        fromPlanet.setName("Mars");
        planetService.createPlanet(fromPlanet);

        Planet toPlanet = new Planet();
        toPlanet.setId("VEN");
        toPlanet.setName("Venus");
        planetService.createPlanet(toPlanet);

        Ticket ticket = new Ticket();
        ticket.setClient(null);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.createTicket(ticket);
        });

        String expectedMessage = "Client cannot be null or have an invalid ID.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateTicketWithInvalidPlanet() {
        Client client = new Client();
        client.setName("Test Client");
        clientService.createClient(client);

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(null);
        ticket.setToPlanet(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.createTicket(ticket);
        });

        String expectedMessage = "FromPlanet and ToPlanet cannot be null.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}