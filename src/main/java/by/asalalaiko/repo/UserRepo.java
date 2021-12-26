package by.asalalaiko.repo;

import by.asalalaiko.domain.User;


import by.asalalaiko.exception.EntityNotFoundException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.UserMapper;

import java.sql.*;
import java.util.Map;


public class UserRepo extends AbstractCRUDRepository<User> {

   private static UserRepo instance;

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

    protected Map<String, String> updateValues(User user) {

        return Map.of(
                "login", "'" + user.getLogin() + "'",
                "password", "'" + user.getPassword() + "'",
                "firstName", "'" + user.getFirstName() + "'",
                "lastName", "'" + user.getLastName() + "'",
                "created_at", "'" + Timestamp.valueOf(user.getCreated()).toString() + "'",
                "locked", "'" + user.getLocked() + "'",
                "email", "'" + user.getEmail() +"'",
                "role", "" + String.valueOf(user.getRole().ordinal())
        );



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
}
