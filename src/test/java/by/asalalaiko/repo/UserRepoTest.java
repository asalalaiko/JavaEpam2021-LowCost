package by.asalalaiko.repo;

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

public class UserRepoTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement createStatement = ConnectionPoolProvider.getConnection().createStatement();

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("init-db.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);

        createStatement.executeUpdate(string);


    }

    @Test
    public void testUserRepoInsertGet(){
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

        assertEquals(user.getLogin(), "admin");

        user.setLogin("user");
        UserRepo.getInstance().save(user);

        assertEquals(user.getLogin(), "user");


    }

}
