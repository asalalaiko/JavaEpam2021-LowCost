package by.asalalaiko.domain;

import by.asalalaiko.repo.WithId;

public class Ticket implements WithId {

    private Long id;
    private String passenger;
    private Boolean baggage;
    private Boolean priority;
    private Flight flight;
    private User user;
    private TicketStatus status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public Boolean getBaggage() {
        return baggage;
    }

    public void setBaggage(Boolean baggage) {
        this.baggage = baggage;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}


