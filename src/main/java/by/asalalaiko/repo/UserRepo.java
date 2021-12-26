package by.asalalaiko.repo;

import by.asalalaiko.domain.User;


import by.asalalaiko.exception.EntityNotFoundException;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class UserRepo extends AbstractCRUDRepository<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepo.class);

    private static UserRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO users (login, password, firstname, lastname, created_at, locked, email, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STATEMENT = "UPDATE users SET login = ?, password = ?, firstname = ? , lastname = ?, created_at = ?, locked = ?, email = ?, role = ? WHERE id = ?";

    private UserRepo() {
        super(new UserMapper(), "users");
        instance = this;
    }

    public static UserRepo getInstance() {
        if (instance == null) {
            UserRepo.instance = new UserRepo();
        }
        return instance;
    }

    public User save(User user) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection();) {

            if (user.getId() == null) {
                ps = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(user, ps);

            if (user.getId() != null) {
                ps.setLong(8, user.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }

            return user;

        } catch (SQLException e) {
            LOGGER.error("Something whent wrong during users retrieval", e);
            throw new EntitySaveException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new EntitySaveException(e);
                }
            }
        }
    }

    public User findByLogin(String login) {
        String SELECT_BY_LOGIN = String.format(SELECT_STATEMENT, " users ").concat("WHERE login = ").concat("'")
                .concat(login).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_LOGIN);

            if (resultSet.next()) {

                User entity = getRm().toObject(resultSet);
                return entity;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user retrieval by login=" + login, e);
            throw new EntityNotFoundException(e);
        }
    }

    private void setValues(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setDate(5, (java.sql.Date) user.getCreated());
        ps.setBoolean(6, user.getLocked());
        ps.setString(7, user.getEmail());
        ps.setInt(8, user.getRole().ordinal());
    }

}
