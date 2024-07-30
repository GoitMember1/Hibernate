package org.example.service;

import org.example.model.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PlanetCrudService {

    public void createPlanet(Planet planet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(planet);
            transaction.commit();
        }
    }

    public Planet getPlanet(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public List<Planet> getAllPlanets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Planet> query = session.createQuery("FROM Planet", Planet.class);
            return query.list();
        }
    }

    public void updatePlanet(Planet planet) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        }
    }

    public void deletePlanet(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
            }
            transaction.commit();
        }
    }
}