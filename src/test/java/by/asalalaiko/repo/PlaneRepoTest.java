package by.asalalaiko.repo;


import by.asalalaiko.domain.Plane;
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

public class PlaneRepoTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("init-db.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);

        createStatement.executeUpdate(string);

    }

    @Test
    public void testPlaneRepoInsertGet() {

        Plane plane = new Plane();
        plane.setName("CCA564");
        plane.setModel("Airbus A350-941");
        plane.setSeats(314);
        plane.setCostKM(BigDecimal.valueOf(150.00));
        PlaneRepo.getInstance().save(plane);

        Plane plane2 = new Plane();
        plane2 = PlaneRepo.getInstance().getById(Long.valueOf(1));
        assertEquals(plane.getName(), plane2.getName());

        plane2.setSeats(250);
        PlaneRepo.getInstance().save(plane2);
        assertEquals(PlaneRepo.getInstance().getById(Long.valueOf(1)).getSeats(), Integer.valueOf(250));

        PlaneRepo.getInstance().deleteById(Long.valueOf(1));
        assertEquals(AirportRepo.getInstance().findAll().size(), 0);

    }
}
