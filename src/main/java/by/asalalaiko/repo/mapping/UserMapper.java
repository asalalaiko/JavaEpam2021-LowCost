package by.asalalaiko.repo.mapping;

import by.asalalaiko.domain.User;
import by.asalalaiko.domain.UsersRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User>{

    @Override
    public User toObject(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getLong("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setCreated(rs.getDate("created"));
        user.setLocked(rs.getBoolean("locked"));
        user.setEmail(rs.getString("email"));
        int ordinal = rs.getInt("role");
        user.setRole(UsersRole.getByOrdinal(ordinal));

        return user;
    }


}

