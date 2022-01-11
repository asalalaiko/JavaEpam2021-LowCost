package by.asalalaiko.repo;


import by.asalalaiko.domain.Ticket;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.TicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
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

    @Override
    protected Map<String, String> updateValues(Ticket ticket) {
        return null;
    }

    private void setValues(Ticket ticket, PreparedStatement ps) throws SQLException {
        ps.setString(1, ticket.getPassenger());
        ps.setBoolean(2, ticket.getBaggage());
        ps.setBoolean(3, ticket.getPriority());
        ps.setLong(4, ticket.getFlight().getId());
        ps.setLong(5, ticket.getUser().getId());
        ps.setString(6,String.valueOf(ticket.getStatus().ordinal()) );

    }
}
