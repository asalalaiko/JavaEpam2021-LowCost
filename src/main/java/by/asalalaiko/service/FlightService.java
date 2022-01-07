package by.asalalaiko.service;

import by.asalalaiko.domain.Airport;
import by.asalalaiko.domain.Flight;
import by.asalalaiko.domain.Plane;
import by.asalalaiko.domain.User;
import by.asalalaiko.repo.AirportRepo;
import by.asalalaiko.repo.FlightRepo;
import by.asalalaiko.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

public class FlightService {

    public static final Logger LOGGER = LoggerFactory.getLogger(FlightService.class);

    private static FlightService instance;

    private FlightService() {

        instance = this;
    }

    public static FlightService getInstance() {
        if (instance == null) {
            FlightService.instance = new FlightService();
        }
        return instance;
    }

    public static Collection<Flight> findAll() {return FlightRepo.getInstance().findAll();    }

    public Flight getById(Long id){
        return FlightRepo.getInstance().getById(id);
    }


    public Flight create(LocalDateTime start, LocalDateTime finish, Integer km, Airport startAirport, Airport finishAirport, Plane plane, BigDecimal cost) {
        Flight flight = new Flight();
        flight.setStart(start);
        flight.setFinish(finish);
        flight.setKm(km);
        flight.setStartAirport(startAirport);
        flight.setFinishAirport(finishAirport);
        flight.setPlane(plane);
        flight.setCost(cost);
        FlightRepo.getInstance().save(flight);
        return flight;
    }

    public Flight update(Long id, LocalDateTime start, LocalDateTime finish, Integer km, Airport startAirport, Airport finishAirport, Plane plane, BigDecimal cost) {
        Flight flight = new Flight();
        flight.setId(id);
        flight.setStart(start);
        flight.setFinish(finish);
        flight.setKm(km);
        flight.setStartAirport(startAirport);
        flight.setFinishAirport(finishAirport);
        flight.setPlane(plane);
        flight.setCost(cost);
        FlightRepo.getInstance().save(flight);
        return flight;
    }
}
