package org.example;



import org.example.model.Client;
import org.example.model.Planet;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;

public class TestCrudOperations {
    public static void main(String[] args) {
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();

        // Test creating clients
        Client client1 = new Client();
        client1.setName("Test Client 1");
        clientService.createClient(client1);

        Client client2 = new Client();
        client2.setName("Test Client 2");
        clientService.createClient(client2);

        // Test retrieving all clients
        System.out.println("All Clients: " + clientService.getAllClients());

        // Test updating a client
        client1.setName("Updated Client 1");
        clientService.updateClient(client1);

        // Test retrieving a single client
        System.out.println("Client with ID 1: " + clientService.getClient(client1.getId()));

        // Test deleting a client
        clientService.deleteClient(client2.getId());

        // Test creating planets
        Planet planet1 = new Planet();
        planet1.setId("NEP");
        planet1.setName("Neptune");
        planetService.createPlanet(planet1);

        Planet planet2 = new Planet();
        planet2.setId("PLU");
        planet2.setName("Pluto");
        planetService.createPlanet(planet2);

        // Test retrieving all planets
        System.out.println("All Planets: " + planetService.getAllPlanets());

        // Test updating a planet
        planet1.setName("Updated Neptune");
        planetService.updatePlanet(planet1);

        // Test retrieving a single planet
        System.out.println("Planet with ID NEP: " + planetService.getPlanet(planet1.getId()));

        // Test deleting a planet
        planetService.deletePlanet(planet2.getId());
    }
}