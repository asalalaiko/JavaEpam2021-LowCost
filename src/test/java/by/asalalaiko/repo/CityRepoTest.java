package by.asalalaiko.repo;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.User;
import by.asalalaiko.domain.UsersRole;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class CityRepoTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("init-db.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);

        createStatement.executeUpdate(string);


    }

    @Test
    public void testPersonRepoInsertGet(){

        City city = new City();
        city.setName("NY");

        CityRepo.getInstance().save(city);

        assertEquals(city.getName(), "NY");

        city.setName("Moscow");
        CityRepo.getInstance().save(city);

        assertEquals(city.getName(), "Moscow");

        CityRepo.getInstance().deleteById(city.getId());
        assertEquals(CityRepo.getInstance().findAll().size(), 0);

    }

}
