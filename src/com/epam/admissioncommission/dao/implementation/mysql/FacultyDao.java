package com.epam.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.admissioncommission.connection.Connector;
import com.epam.admissioncommission.dao.IFacultyDao;
import com.epam.admissioncommission.enteties.Faculty;

public class FacultyDao implements IFacultyDao {
	private static final String QUERY_FOR_DELETE = "DELETE FROM faculty WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE faculty SET name = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO faculty(name, students_number, short_name) VALUES (?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM faculty";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM faculty WHERE id = ";
	
	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
		}
	}

	@Override
	public List<Faculty> selectAll() {
		List<Faculty> faculties = new ArrayList<Faculty>();
		Faculty currentFaculty;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				currentFaculty = new Faculty();
				currentFaculty.setId(result.getInt("id"));
				currentFaculty.setName(result.getString("name"));
				currentFaculty.setShortName(result.getString("short_name"));
				currentFaculty.setStudentsNumber(result.getInt("students_number"));
				faculties.add(currentFaculty);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
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
				currentFaculty = new Faculty();
				currentFaculty.setId(result.getInt("id"));
				currentFaculty.setName(result.getString("name"));
				currentFaculty.setShortName(result.getString("short_name"));
				currentFaculty.setStudentsNumber(result.getInt("students_number"));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return currentFaculty;
	}

}
