package by.asalalaiko.repo.mapping;

import by.asalalaiko.domain.Airport;
import by.asalalaiko.repo.CityRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportMapper implements RowMapper<Airport>{

    @Override
    public Airport toObject(ResultSet rs) throws SQLException {
        Airport airport = new Airport();

        airport.setId(rs.getLong("id"));
        airport.setName(rs.getString("name"));
        airport.setTax(rs.getBigDecimal("tax"));
        long ordinal = rs.getLong("city");
        airport.setCity(CityRepo.getInstance().getById(ordinal));

        return airport;
    }
}
