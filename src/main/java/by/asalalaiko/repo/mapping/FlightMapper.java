package by.asalalaiko.repo.mapping;


import by.asalalaiko.domain.Flight;
import by.asalalaiko.repo.AirportRepo;
import by.asalalaiko.repo.PlaneRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightMapper implements RowMapper<Flight>{

    @Override
    public Flight toObject(ResultSet rs) throws SQLException {
        Flight flight = new Flight();

        flight.setId(rs.getLong("id"));
        flight.setStart(rs.getTimestamp("start_date").toLocalDateTime());
        flight.setFinish(rs.getTimestamp("end_date").toLocalDateTime());
        flight.setKm(rs.getInt("km"));
        flight.setStartAirport(AirportRepo.getInstance().getById(rs.getLong("airport_start_id")));
        flight.setFinishAirport(AirportRepo.getInstance().getById(rs.getLong("airport_end_id")));
        flight.setPlane(PlaneRepo.getInstance().getById(rs.getLong("plane_id")));
        flight.setCost(rs.getBigDecimal("cost"));

        return flight;
    }
}
