package by.asalalaiko.repo;

import by.asalalaiko.domain.City;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;

public class CityRepo extends AbstractCRUDRepository<City>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CityRepo.class);

    private static CityRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO city (name) VALUES (?)";
    private static final String UPDATE_STATEMENT = "UPDATE city SET name = ? WHERE id = ?";

    public CityRepo() {
        super(new CityMapper(), "city");
        instance = this;
    }

    public static CityRepo getInstance() {
        if (instance == null) {
            CityRepo.instance = new CityRepo();
        }
        return instance;
    }

    public City save(City city) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection();) {

            if (city.getId() == null) {
                ps = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(city, ps);

            if (city.getId() != null) {
                ps.setLong(2, city.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                city.setId(generatedKeys.getLong(1));
            }

            return city;

        } catch (SQLException e) {
            LOGGER.error("Something whent wrong during users retrieval", e);
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
    protected Map<String, String> updateValues(City person) {
        return null;
    }

    private void setValues(City city, PreparedStatement ps) throws SQLException {
        ps.setString(1, city.getName());
    }

}
