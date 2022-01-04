package by.asalalaiko.repo;



import by.asalalaiko.domain.Plane;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.PlaneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;

public class PlaneRepo extends AbstractCRUDRepository<Plane>{

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaneRepo.class);

    private static PlaneRepo instance;

    private static final String INSERT_STATEMENT = "INSERT INTO plane (name, model, passenger_seats, cost_1km) VALUES (?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE plane SET name = ?, model=?, passenger_seats=?, cost_1km=? WHERE id = ?";


    public PlaneRepo() {
        super(new PlaneMapper(), "plane");
        instance = this;
    }

    public static PlaneRepo getInstance() {
        if (instance == null) {
            PlaneRepo.instance = new PlaneRepo();
        }
        return instance;
    }

    public Plane save(Plane plane) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            if (plane.getId() == null) {
                ps = connection.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(plane, ps);

            if (plane.getId() != null) {
                ps.setLong(5, plane.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                plane.setId(generatedKeys.getLong(1));
            }

            return plane;

        } catch (SQLException e) {
            LOGGER.error("Something whent wrong during planes retrieval", e);
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
    protected Map<String, String> updateValues(Plane plane) {
        return null;
    }

    private void setValues(Plane plane, PreparedStatement ps) throws SQLException {
        ps.setString(1, plane.getName());
        ps.setString(2, plane.getModel());
        ps.setLong(3, plane.getSeats());
        ps.setBigDecimal(4, plane.getCostKM());

    }

}
