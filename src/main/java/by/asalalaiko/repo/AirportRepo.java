package by.asalalaiko.repo;

import by.asalalaiko.domain.Airport;
import by.asalalaiko.exception.EntityNotFoundException;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.AirportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AirportRepo extends AbstractCRUDRepository<Airport>{

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportRepo.class);

    private static AirportRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO airport (name, tax, city_id) VALUES (?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE airport SET name = ?, tax=?, city_id=? WHERE id = ?";


    public AirportRepo() {
        super(new AirportMapper(), "airport");
        instance = this;
    }

    public static AirportRepo getInstance() {
        if (instance == null) {
            AirportRepo.instance = new AirportRepo();
        }
        return instance;
    }

    public Airport save(Airport airport) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection();) {

            if (airport.getId() == null) {
                ps = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(airport, ps);

            if (airport.getId() != null) {
                ps.setLong(4, airport.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                airport.setId(generatedKeys.getLong(1));
            }

            return airport;

        } catch (SQLException e) {
            LOGGER.error("Something whent wrong during airports retrieval", e);
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
    protected Map<String, String> updateValues(Airport airport) {
        return null;
    }

    private void setValues(Airport airport, PreparedStatement ps) throws SQLException {
        ps.setString(1, airport.getName());
        ps.setBigDecimal(2, airport.getTax());
        ps.setLong(3, airport.getCity().getId());
    }

    public List<Airport> getByCityId(String cityID) {


        String SELECT_BY_CITY_ID = String.format(SELECT_STATEMENT, " airport ").concat("WHERE city_id = ").concat(cityID);
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_CITY_ID);

            List<Airport> entities = new ArrayList<>();

            while  (resultSet.next()) {

                entities.add(rm.toObject(resultSet));
            }
            return entities;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during Airport retrieval by cityID=" + cityID, e);
            throw new EntityNotFoundException(e);
        }
    }
}
