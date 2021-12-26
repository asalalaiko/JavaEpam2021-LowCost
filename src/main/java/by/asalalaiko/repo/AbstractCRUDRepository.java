package by.asalalaiko.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import by.asalalaiko.exception.EntityDeleteException;
import by.asalalaiko.exception.EntityNotFoundException;
import by.asalalaiko.exception.EntityRetrieveException;
import by.asalalaiko.repo.jdbc.ConnectionPoolProvider;
import by.asalalaiko.repo.mapping.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractCRUDRepository<T> {

	public static final Logger LOGGER = LoggerFactory.getLogger(AbstractCRUDRepository.class);
	public static final String SELECT_STATEMENT = "SELECT * FROM %s";
	public static final String DELETE_STATEMENT = "DELETE FROM %s WHERE id = %d";
	public static final String SELECT_BY_ID_SQL = SELECT_STATEMENT + " WHERE id = %d";
	public static final String ORDERED_PAGINATED = SELECT_STATEMENT + " ORDER BY %s LIMIT %d OFFSET %d";

	private RowMapper<T> rm;
	private String tableName;

	public AbstractCRUDRepository(RowMapper<T> rm, String tableName) {
		super();
		this.rm = rm;
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

	public RowMapper<T> getRm() {
		return rm;
	}



}
