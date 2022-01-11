package by.asalalaiko.repo.mapping;

import by.asalalaiko.domain.Ticket;
import by.asalalaiko.domain.TicketStatus;
import by.asalalaiko.repo.FlightRepo;
import by.asalalaiko.repo.UserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper  implements RowMapper<Ticket>{
    @Override
    public Ticket toObject(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        ticket.setPassenger(rs.getString("passenger"));
        ticket.setBaggage(rs.getBoolean("baggage"));
        ticket.setPriority(rs.getBoolean("priority"));
        ticket.setFlight(FlightRepo.getInstance().getById(rs.getLong("flight_id")));
        ticket.setUser(UserRepo.getInstance().getById(rs.getLong("user_id")));
        ticket.setStatus(TicketStatus.getByOrdinal(rs.getInt("status")));

        return ticket;
    }
}