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
import com.admissioncommission.dao.IApplicationMarkDao;
import com.admissioncommission.enteties.ApplicationMark;

public class ApplicationMarkDao implements IApplicationMarkDao {
	private static final String APPLICATION_ID = "application_id";
	private static final String MARK_ID = "mark_id";
	private static final String QUERY_FOR_DELETE = "DELETE FROM application_mark WHERE application_id = ? AND mark_id = ?";
	private static final String QUERY_FOR_UPDATE_BY_APPLICATION_ID = "UPDATE application_mark SET mark_id = ? "
			+ "WHERE application_id = ?";
	private static final String QUERY_FOR_UPDATE_BY_MARK_ID = "UPDATE application_mark SET application_id = ?"
			+ "WHERE mark_id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO application_mark(application_id, mark_id) VALUES(?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM application_mark";
	private static final String QUERY_FOR_FIND_BY_APPLICATION_ID = "SELECT * FROM application_mark WHERE application_id = ";
	private static final String QUERY_FOR_FIND_BY_MARK_ID = "SELECT * FROM application_mark WHERE mark_id = ";
	
	private static final Logger LOGGER = LogManager.getLogger(ApplicationMarkDao.class);
	
	@Override
	public void delete(int applicationId, int markId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE);){
			statement.setInt(1, applicationId);
			statement.setInt(2, markId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing mark", exception);
		}
	}

	@Override
	public void updateByApplicationId(int applicationId, int newMarkId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE_BY_APPLICATION_ID);){
			statement.setInt(1, newMarkId);
			statement.setInt(2, applicationId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing mark by application id", exception);
		}
	}

	@Override
	public void updateByMarkId(int markId, int newApplicationId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE_BY_MARK_ID);){
			statement.setInt(1, newApplicationId);
			statement.setInt(2, markId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing mark by id", exception);
		}
	}

	@Override
	public void insert(ApplicationMark newApplicationMark) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT);){
			statement.setInt(1, newApplicationMark.getApplicationId());
			statement.setInt(2, newApplicationMark.getMarkId());
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error inserting into table ", exception);
		}
	}

	@Override
	public List<ApplicationMark> selectAll() {
		List<ApplicationMark> applicationMarks = new ArrayList<ApplicationMark>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				applicationMarks.add(setApplicationMark(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
		}
		
		return applicationMarks;
	}

	@Override
	public List<ApplicationMark> findByApplicationId(int applicationId) {
		List<ApplicationMark> applicationMarks = new ArrayList<ApplicationMark>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_APPLICATION_ID + applicationId)) {
			while(result.next()){
				applicationMarks.add(setApplicationMark(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by application id", exception);
		}
		
		return applicationMarks;
	}

	@Override
	public List<ApplicationMark> findbyMarkid(int markId) {
		List<ApplicationMark> applicationMarks = new ArrayList<ApplicationMark>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_MARK_ID +  markId)) {
			while(result.next()){
				applicationMarks.add(setApplicationMark(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by mark id", exception);
		}
		
		return applicationMarks;
	}

	private ApplicationMark setApplicationMark(ResultSet result) throws SQLException{
		ApplicationMark applicationMark = new ApplicationMark();
		applicationMark.setApplicationId(result.getInt(APPLICATION_ID));
		applicationMark.setMarkId(result.getInt(MARK_ID));
		return applicationMark;
	}
}
