package by.asalalaiko.repo;


import by.asalalaiko.domain.Ticket;
import by.asalalaiko.domain.TicketStatus;
import by.asalalaiko.domain.User;
import by.asalalaiko.exception.EntityNotFoundException;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.TicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TicketRepo  extends AbstractCRUDRepository<Ticket>{

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRepo.class);

    private static TicketRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO ticket (passenger, baggage, priority, flight_id, user_id, status) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE ticket SET passenger = ?, baggage=?, priority=?, flight_id=?, user_id=?, status=? WHERE id = ?";


    public TicketRepo() {
        super(new TicketMapper(), "ticket");
        instance = this;
    }

    public static TicketRepo getInstance() {
        if (instance == null) {
            TicketRepo.instance = new TicketRepo();
        }
        return instance;
    }

    public Ticket save(Ticket ticket) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            if (ticket.getId() == null) {
                ps = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(ticket, ps);

            if (ticket.getId() != null) {
                ps.setLong(7, ticket.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                ticket.setId(generatedKeys.getLong(1));
            }

            return ticket;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during tickets retrieval", e);
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

    public Collection<Ticket> findByFlightIdAndStatus(Long id, TicketStatus status){

        String idStr = String.valueOf(id);
        String statusSTR = String.valueOf(status.ordinal());

        String SELECT_BY_FLIGHT_AND_STATUS = String.format(SELECT_STATEMENT, " ticket ").concat("WHERE flight_id = ").concat("'")
                .concat(idStr).concat("' AND status = ").concat("'")
                .concat(statusSTR).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_FLIGHT_AND_STATUS);

            List<Ticket> entities = new ArrayList<>();

            while  (resultSet.next()) {

                entities.add(rm.toObject(resultSet));
            }
                return entities;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during FlightId retrieval by login=" + id+", status="+ status, e);
            throw new EntityNotFoundException(e);
        }
    }
    public Collection<Ticket> findByFlightId(Long id){

        String idStr = String.valueOf(id);


        String SELECT_BY_FLIGHT = String.format(SELECT_STATEMENT, " ticket ").concat("WHERE flight_id = ").concat("'")
                .concat(idStr).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_FLIGHT);

            List<Ticket> entities = new ArrayList<>();

            while  (resultSet.next()) {

                entities.add(rm.toObject(resultSet));
            }
            return entities;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during FlightId retrieval by login=" + id, e);
            throw new EntityNotFoundException(e);
        }
    }

    @Override
    protected Map<String, String> updateValues(Ticket ticket) {

        return Map.of(
                "passenger", "'" + ticket.getPassenger() + "'",
                "baggage", "'" + ticket.getBaggage() + "'",
                "priority", "'" + ticket.getPriority() + "'",
                "flight_id", "'" + ticket.getFlight().getId() + "'",
                "user_id", "'" + ticket.getUser().getId() + "'",
                "role", "'" + String.valueOf(ticket.getStatus().ordinal()) +"'"

        );
    }

    private void setValues(Ticket ticket, PreparedStatement ps) throws SQLException {
        ps.setString(1, ticket.getPassenger());
        ps.setBoolean(2, ticket.getBaggage());
        ps.setBoolean(3, ticket.getPriority());
        ps.setLong(4, ticket.getFlight().getId());
        ps.setLong(5, ticket.getUser().getId());
        ps.setString(6,String.valueOf(ticket.getStatus().ordinal()) );

    }

    public Collection<Ticket> findByUser(User user) {
        String idStr = String.valueOf(user.getId());


        String SELECT_BY_USER = String.format(SELECT_STATEMENT, " ticket ").concat("WHERE user_id = ").concat("'")
                .concat(idStr).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_USER);

            List<Ticket> entities = new ArrayList<>();

            while  (resultSet.next()) {

                entities.add(rm.toObject(resultSet));
            }
            return entities;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user_id retrieval by login=" + idStr, e);
            throw new EntityNotFoundException(e);
        }
    }
}
