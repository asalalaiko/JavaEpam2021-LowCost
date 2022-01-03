package by.asalalaiko.repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import by.asalalaiko.exception.EntityDeleteException;
import by.asalalaiko.exception.EntityNotFoundException;
import by.asalalaiko.exception.EntityRetrieveException;
import by.asalalaiko.exception.EntitySaveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractCRUDRepository<T extends WithId> {
	public static final Logger LOGGER = LoggerFactory.getLogger(AbstractCRUDRepository.class);
	public static final String SELECT_STATEMENT = "SELECT * FROM %s";
	public static final String DELETE_STATEMENT = "DELETE FROM %s WHERE id = %d";
	public static final String SELECT_BY_ID_SQL = SELECT_STATEMENT + " WHERE id = %d";
	public static final String ORDERED_PAGINATED = SELECT_STATEMENT + " ORDER BY %s LIMIT %d OFFSET %d";

	public static final String INSERT_STATEMENT = "INSERT INTO %s (%s) VALUES (%s)";
	public static final String UPDATE_STATEMENT = "UPDATE %s SET %s WHERE id = %s";

	protected RowMapper<T> rm;
	protected String tableName;

	public AbstractCRUDRepository(RowMapper<T> rs, String tableName) {
		super();
		this.rm = rs;
		this.tableName = tableName;
	}

	public T getById(Long id) {

		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			ResultSet resultSet = connection.createStatement()
					.executeQuery(String.format(SELECT_BY_ID_SQL, tableName, id));

			if (resultSet.next()) {

				T entity = rm.toObject(resultSet);
				return entity;
			} else {
				return null;
			}

		} catch (SQLException e) {
			LOGGER.error("Something went wrong during user retrieval by id=" + id, e);
			throw new EntityNotFoundException();
		}
	}

	public List<T> findAll() {

		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			ResultSet resultSet = connection.createStatement().executeQuery(String.format(SELECT_STATEMENT, tableName));

			List<T> entities = new ArrayList<>();

			while (resultSet.next()) {
				entities.add(rm.toObject(resultSet));
			}

			return entities;

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during users retrieval", e);
			throw new EntityRetrieveException(e);
		}
	}

	public void deleteById(Long id) {

		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			int rowsAffected = connection.createStatement()
					.executeUpdate(String.format(DELETE_STATEMENT, tableName, id));

			if (rowsAffected != 1) {
				throw new EntityDeleteException("Entity not deleted");
			}

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during users retrieval", e);
			throw new EntityRetrieveException(e);
		}
	}

	public List<T> findAllSorded(String fieldName, Integer limit, Integer offset) {
		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			ResultSet resultSet = connection.createStatement()
					.executeQuery(String.format(ORDERED_PAGINATED, tableName, fieldName, limit, offset));

			List<T> entities = new ArrayList<>();

			while (resultSet.next()) {
				entities.add(rm.toObject(resultSet));
			}

			return entities;

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during users retrieval", e);
			throw new EntityRetrieveException(e);
		}
	}

	protected void update(Long id, Map<String, String> toSet) {
		PreparedStatement ps = null;
		try (Connection connection = ConnectionPoolProvider.getConnection()) {

			String pairs = toSet.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue())
					.collect(Collectors.joining(","));

			ps = connection.prepareStatement(String.format(UPDATE_STATEMENT, tableName, pairs, id));

			if (ps.executeUpdate() != 1) {
				throw new EntitySaveException("Something went wrong");
			}

		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during data saving", e);
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

	protected Long create(Map<String, String> toSet) {

		PreparedStatement ps = null;
		try (Connection connection = ConnectionPoolProvider.getConnection();) {

			String csvColumns = toSet.entrySet().stream().map(e -> e.getKey()).collect(Collectors.joining(","));
			String csvValues = toSet.entrySet().stream().map(e -> e.getValue()).collect(Collectors.joining(","));

			ps = connection.prepareStatement(String.format(INSERT_STATEMENT, tableName, csvColumns, csvValues),
					Statement.RETURN_GENERATED_KEYS);

			if (ps.executeUpdate() != 1) {
				throw new EntitySaveException("Something went wrong");
			}

			ResultSet generatedKeys = ps.getGeneratedKeys();

			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new EntitySaveException("Something went wrong");
			}
		} catch (SQLException e) {
			LOGGER.error("Something whent wrong during data saving retrieval", e);
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

	public T save(T user) {
		if (user.getId() == null) {
			Long create = create(updateValues(user));
			user.setId(create);
			return user;
		} else {
			update(user.getId(), updateValues(user));
			return user;
		}
	}

	public RowMapper<T> getRm() {
		return rm;
	}

	protected abstract Map<String, String> updateValues(T user);
}
