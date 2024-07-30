package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().load();
        flyway.migrate();

        System.out.println("Migrations completed successfully!");
    }
}