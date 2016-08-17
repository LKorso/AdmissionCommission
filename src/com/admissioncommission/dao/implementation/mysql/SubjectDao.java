package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.ISubjectDao;
import com.admissioncommission.enteties.Subject;

public class SubjectDao implements ISubjectDao {
	private static final String QUERY_FOR_DELETE = "DELETE FROM subject WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE subject SET name = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO subject(id, name) VALUES (?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM subject";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM subject WHERE id = ";
	private static final String QUERY_FOR_FIND_BY_NAME = "SELECT * FROM subject WHERE name = \"";

	private static final Logger LOGGER = LogManager.getLogger(SubjectDao.class);
	
	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing subject", exception);
		}
	}

	@Override
	public void update(int id, String newName) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {
			statement.setString(1, newName);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error updating mark", exception);
		}
	}

	@Override
	public void insert(Subject newSubject) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setInt(1, newSubject.getId());
			statement.setString(2, newSubject.getName());
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error inserting into table ", exception);
		}
	}

	@Override
	public List<Subject> selectAll() {
		List<Subject> subjects = new ArrayList<Subject>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				subjects.add(seSubject(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
		}
		
		return subjects;
	}

	@Override
	public Subject findById(int id) {
		Subject currentSubject = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentSubject = seSubject(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by id", exception);
		}
		
		return currentSubject;
	}
	
	
	@Override
	public Subject findByName(String name) {
		Subject currentSubject = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_NAME + name + "\"")) {
			while(result.next()){
				currentSubject = seSubject(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by name", exception);
		}
		
		return currentSubject;
	}
	
	private Subject seSubject(ResultSet result) throws SQLException{
		Subject currentSubject = new Subject();
		currentSubject.setId(result.getInt("id"));
		currentSubject.setName(result.getString("name"));
		return currentSubject;
	}
}
