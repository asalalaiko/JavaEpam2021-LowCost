package by.asalalaiko.service;


import by.asalalaiko.domain.Flight;
import by.asalalaiko.domain.Ticket;
import by.asalalaiko.domain.TicketStatus;
import by.asalalaiko.domain.User;
import by.asalalaiko.repo.TicketRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class TicketService {

    public static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    private static TicketService instance;

    private TicketService() {

        instance = this;
    }

    public static TicketService getInstance() {
        if (instance == null) {
            TicketService.instance = new TicketService();
        }
        return instance;
    }

    public static Collection<Ticket> findAll() {return TicketRepo.getInstance().findAll();
    }

    public static Collection<Ticket> findByFlightIdAndStatus(Long id, TicketStatus status) {return TicketRepo.getInstance().findByFlightIdAndStatus(id, status);
    }


    public Ticket create(String passenger, Boolean baggage, Boolean priority, Flight flight, User user, TicketStatus status) {
          Ticket ticket = new Ticket();
          ticket.setPassenger(passenger);
          ticket.setBaggage(baggage);
          ticket.setPriority(priority);
          ticket.setFlight(flight);
          ticket.setUser(user);
          ticket.setStatus(status);

        TicketRepo.getInstance().save(ticket);
        return ticket;
    }

    public Ticket update(Long id, String passenger, Boolean baggage, Boolean priority, Flight flight, User user, TicketStatus status) {
        Ticket ticket = new Ticket();

        ticket.setId(id);
        ticket.setPassenger(passenger);
        ticket.setBaggage(baggage);
        ticket.setPriority(priority);
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setStatus(status);
        TicketRepo.getInstance().save(ticket);
        return ticket;
    }

    public Ticket getById(Long id){
        return TicketRepo.getInstance().getById(id);
    }
}


