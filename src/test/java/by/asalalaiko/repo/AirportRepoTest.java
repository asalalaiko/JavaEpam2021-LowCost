package by.asalalaiko.repo;

import by.asalalaiko.domain.Airport;
import by.asalalaiko.domain.City;
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

import static org.junit.Assert.assertEquals;

public class AirportRepoTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("init-db.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);

        createStatement.executeUpdate(string);


    }

    @Test
    public void testAirportRepoInsertGet(){

        City city = new City();
        city.setName("NY");
        CityRepo.getInstance().save(city);

        Airport airport = new Airport();
        airport.setName("JFK International Airport");
        airport.setTax(BigDecimal.valueOf(1500.00));
        airport.setCity(city);
        AirportRepo.getInstance().save(airport);

        assertEquals(airport.getName(), "JFK International Airport");
        assertEquals(airport.getCity().getName(), "NY");

        airport.setTax(BigDecimal.valueOf(10.00));
        AirportRepo.getInstance().save(airport);

        assertEquals(airport.getTax(), BigDecimal.valueOf(10.00));


        AirportRepo.getInstance().deleteById(airport.getId());
        assertEquals(AirportRepo.getInstance().findAll().size(), 0);

    }


}
