package by.asalalaiko.repo;

import by.asalalaiko.domain.*;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TicketRepoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("init-db.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);

        createStatement.executeUpdate(string);


    }

    @Test
    public void testTicketRepoGet(){
        setAirport1();
        setAirport2();
        setPlane();
        Flight flight = setFlight();
        User user = setUser();

            Ticket ticket = new Ticket();
            ticket.setPassenger("");
            ticket.setBaggage(Boolean.FALSE);
            ticket.setPriority(Boolean.FALSE);
            ticket.setFlight(flight);
            ticket.setStatus(TicketStatus.FREE);
            ticket.setUser(user);
            TicketRepo.getInstance().save(ticket);

        assertEquals(TicketRepo.getInstance().findAll().size(), 1);


    }

    private User setUser() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setFirstName("Admin");
        user.setLastName("Adminov");
        user.setLocked(Boolean.FALSE);
        user.setCreated(timestamp.toLocalDateTime());
        user.setLogin("admin");
        user.setPassword("admin");
        user.setRole(UsersRole.ADMIN);
        user.setActive(Boolean.FALSE);
        user.setActionCode("0123456789");

        UserRepo.getInstance().save(user);

        return user;
    }


    public static void setAirport1(){
        City city = new City();
        city.setName("NY");
        CityRepo.getInstance().save(city);

        Airport airport = new Airport();
        airport.setName("JFK International Airport");
        airport.setTax(BigDecimal.valueOf(1500.00));
        airport.setCity(city);
        AirportRepo.getInstance().save(airport);

    }

    public static void setAirport2(){
        City city = new City();
        city.setName("Berlin");
        CityRepo.getInstance().save(city);

        Airport airport = new Airport();
        airport.setName("Brandenburg Airport Willy Brandt");
        airport.setTax(BigDecimal.valueOf(1000.00));
        airport.setCity(city);
        AirportRepo.getInstance().save(airport);
    }

    public static void setPlane(){
        Plane plane = new Plane();
        plane.setName("CCA564");
        plane.setModel("Airbus A350-941");
        plane.setSeats(314);
        plane.setCostKM(BigDecimal.valueOf(150.00));
        PlaneRepo.getInstance().save(plane);
    }

    public static Flight setFlight(){
    Flight flight = new Flight();

        flight.setStart(LocalDateTime.parse("2022-08-04T10:11:00"));
        flight.setFinish(LocalDateTime.parse("2022-08-05T12:50:00"));
        flight.setKm(3000);
        flight.setStartAirport(AirportRepo.getInstance().getById(1L));
        flight.setFinishAirport(AirportRepo.getInstance().getById(2L));
        flight.setPlane(PlaneRepo.getInstance().getById(1L));
        flight.setCost(BigDecimal.valueOf(10000.00));

        FlightRepo.getInstance().save(flight);
        return flight;
    }
}
