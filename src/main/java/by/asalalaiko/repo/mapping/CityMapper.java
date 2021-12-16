package by.asalalaiko.repo.mapping;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.User;
import by.asalalaiko.domain.UsersRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements RowMapper<City>{
    @Override
    public City toObject(ResultSet rs) throws SQLException {

        City city = new City();

        city.setId(rs.getLong("id"));
        city.setName(rs.getString("name"));

        return city;
    }
}
