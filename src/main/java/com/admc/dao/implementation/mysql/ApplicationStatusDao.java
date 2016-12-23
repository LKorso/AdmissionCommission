package com.admc.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.admc.connection.Connector;
import com.admc.dao.IApplicationStatusDao;
import com.admc.enteties.ApplicationStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ApplicationStatusDao implements IApplicationStatusDao {
	private static final String ID = "id";
	private static final String STATUS = "status";
	private static final String QUERY_FOR_DELETE = "DELETE FROM application_status WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE application_status SET status = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO application_status (status) VALUES(?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM application_status";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM application_status WHERE id = ";
	private static final String QUERY_FOR_FIND_BY_STATUS = "SELECT * FROM application_status WHERE status = \"";
	
	private static final Logger LOGGER = LogManager.getLogger(ApplicationStatusDao.class);
	
	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE);){
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing status", exception);
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
			LOGGER.error("Error updating status", exception);
		}
	}

	@Override
	public void insert(ApplicationStatus newStatus) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT);){
			statement.setString(1, newStatus.getStatus());
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error inserting into table ", exception);
		}

	}

	@Override
	public List<ApplicationStatus> selectAll() {
		List<ApplicationStatus> statuses = new ArrayList<>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				statuses.add(setStatus(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
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
			LOGGER.error("Error selecting from table by id", exception);
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
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by status", exception);
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
