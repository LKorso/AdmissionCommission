package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IPriorityDao;
import com.admissioncommission.enteties.Priority;

public class PriorityDao implements IPriorityDao {
	private static final String QUERY_FOR_DELETE = "DELETE FROM priority WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE priority SET priority = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO priority(priority) VALUES (?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM priority";
	private static final String QUERY_FOR_SELECT_ALL_SORTED = "SELECT * FROM priority ORDER BY priority";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM priority WHERE id = ";
	
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
	public void update(int id, String newPriority) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {
			statement.setString(1, newPriority);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void insert(Priority newPriority) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setInt(1, newPriority.getPriority());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	@Override
	public List<Priority> selectAll() {
		List<Priority> pririties = new ArrayList<Priority>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				pririties.add(setPriority(result));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return pririties;
	}

	@Override
	public List<Priority> selectAllSorted() {
		List<Priority> pririties = new ArrayList<Priority>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL_SORTED)) {
			while(result.next()){
				pririties.add(setPriority(result));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return pririties;
	}
	
	@Override
	public Priority findById(int id) {
		Priority currentPriority = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentPriority = setPriority(result);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return currentPriority;
	}
	
	private Priority setPriority(ResultSet result) throws SQLException{
		Priority currentPriority = new Priority();
		currentPriority.setId(result.getInt("id"));
		currentPriority.setPriority(result.getInt("priority"));
		return currentPriority;
	}

}
