package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IApplicationStatusDao;
import com.admissioncommission.enteties.ApplicationStatus;

public class ApplicationStatusDao implements IApplicationStatusDao {
	public static final String ID = "id";
	public static final String STATUS = "status";
	
	private static final String QUERY_FOR_DELETE = "DELETE FROM application_status WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE application_status SET status = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO application_status (status) VALUES(?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM application_status";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM application_status WHERE id = ";
	private static final String QUERY_FOR_FIND_BY_STATUS = "SELECT * FROM application_status WHERE status = \"";

	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE);){
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void update(int id, String newStatus) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE);){
			statement.setString(1, newStatus);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void insert(ApplicationStatus newStatus) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT);){
			statement.setString(1, newStatus.getStatus());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	@Override
	public List<ApplicationStatus> selectAll() {
		List<ApplicationStatus> statuses = new ArrayList<ApplicationStatus>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				statuses.add(setStatus(result));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return statuses;
	}

	@Override
	public ApplicationStatus findById(int id) {
		ApplicationStatus currentStatus = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentStatus = setStatus(result);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return currentStatus;
	}

	@Override
	public ApplicationStatus findByStatus(String status) {
		ApplicationStatus currentStatus = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_STATUS + status + "\"")) {
			while(result.next()){
				currentStatus = setStatus(result);
				System.out.println(currentStatus);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return currentStatus;
	}
	
	private ApplicationStatus setStatus(ResultSet result) throws SQLException{
		ApplicationStatus currentStatus = new ApplicationStatus();
		
		currentStatus.setId(result.getInt(ID));
		currentStatus.setStatus(result.getString(STATUS));
	
		return currentStatus;
	}

}
