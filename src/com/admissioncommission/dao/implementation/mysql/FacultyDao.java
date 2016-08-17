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
import com.admissioncommission.dao.IFacultyDao;
import com.admissioncommission.enteties.Faculty;

public class FacultyDao implements IFacultyDao {
	private static final String QUERY_FOR_DELETE = "DELETE FROM faculty WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE faculty SET name = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO faculty(name, students_number, short_name) VALUES (?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM faculty";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM faculty WHERE id = ";

	private static final Logger LOGGER = LogManager.getLogger(FacultyDao.class);
	
	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing faculty", exception);
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
			LOGGER.error("Error updating faculty", exception);
		}
	}

	@Override
	public void insert(Faculty newFaculty) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setString(1, newFaculty.getName());
			statement.setInt(2, newFaculty.getStudentsNumber());
			statement.setString(3, newFaculty.getShortName());
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error inserting into table ", exception);
		}
	}

	@Override
	public List<Faculty> selectAll() {
		List<Faculty> faculties = new ArrayList<Faculty>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				faculties.add(setFaculty(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
		}
		
		return faculties;
	}

	@Override
	public Faculty findById(int id) {
		Faculty currentFaculty = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentFaculty = setFaculty(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by id", exception);
		}
		
		return currentFaculty;
	}

	private Faculty setFaculty(ResultSet result) throws SQLException{
		Faculty currentFaculty = new Faculty();
		currentFaculty.setId(result.getInt("id"));
		currentFaculty.setName(result.getString("name"));
		currentFaculty.setShortName(result.getString("short_name"));
		currentFaculty.setStudentsNumber(result.getInt("students_number"));
		return currentFaculty;
	}
}
