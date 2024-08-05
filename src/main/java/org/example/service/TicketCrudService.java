package org.example.service;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class TicketCrudService {

    public void createTicket(Ticket ticket) {
        validateTicket(ticket);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            ticket.setCreatedAt(LocalDateTime.now());
            session.save(ticket);
            transaction.commit();
        }
    }

    public Ticket getTicket(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    public void updateTicket(Ticket ticket) {
        validateTicket(ticket);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        }
    }

    public void deleteTicket(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            }
            transaction.commit();
        }
    }

    private void validateTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getClient().getId() == 0) {
            throw new IllegalArgumentException("Client cannot be null or have an invalid ID.");
        }
        if (ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("FromPlanet and ToPlanet cannot be null.");
        }
    }
}