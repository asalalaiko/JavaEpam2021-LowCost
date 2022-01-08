package by.asalalaiko.repo;


import by.asalalaiko.domain.Flight;
import by.asalalaiko.domain.Plane;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.FlightMapper;
import by.asalalaiko.repo.mapping.PlaneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;

public class FlightRepo extends AbstractCRUDRepository<Flight>{

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRepo.class);

    private static FlightRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO flight (start_date, end_date, km, airport_start_id, airport_end_id, plane_id, cost) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE flight SET start_date = ?, end_date=?, km=?, airport_start_id=?, airport_end_id=?, plane_id=?, cost=? WHERE id = ?";


    public FlightRepo() {
        super(new FlightMapper(), "flight");
        instance = this;
    }

    public static FlightRepo getInstance() {
        if (instance == null) {
            FlightRepo.instance = new FlightRepo();
        }
        return instance;
    }

    public Flight save(Flight flight) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            if (flight.getId() == null) {
                ps = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(flight, ps);

            if (flight.getId() != null) {
                ps.setLong(8, flight.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                flight.setId(generatedKeys.getLong(1));
            }

            return flight;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during flights retrieval", e);
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
    protected Map<String, String> updateValues(Flight flight) {
        return null;
    }

    private void setValues(Flight flight, PreparedStatement ps) throws SQLException {
        ps.setTimestamp(1, Timestamp.valueOf(flight.getStart()));
        ps.setTimestamp(2, Timestamp.valueOf(flight.getFinish()));
        ps.setLong(3, flight.getKm());
        ps.setLong(4, flight.getStartAirport().getId());
        ps.setLong(5, flight.getFinishAirport().getId());
        ps.setLong(6, flight.getPlane().getId());
        ps.setBigDecimal(7,flight.getCost());
    }
}
