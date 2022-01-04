package by.asalalaiko.repo.mapping;

import by.asalalaiko.domain.Plane;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaneMapper implements RowMapper<Plane>{
    @Override
    public Plane toObject(ResultSet rs) throws SQLException {
        Plane plane = new Plane();

        plane.setId(rs.getLong("id"));
        plane.setName(rs.getString("name"));
        plane.setModel(rs.getString("model"));
        plane.setSeats(rs.getInt("passenger_seats"));
        plane.setCostKM(rs.getBigDecimal("cost_1km"));

        return plane;
    }
}
