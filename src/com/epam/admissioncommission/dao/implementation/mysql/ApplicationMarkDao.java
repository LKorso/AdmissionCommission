package com.epam.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.admissioncommission.connection.Connector;
import com.epam.admissioncommission.dao.IApplicationMarkDao;
import com.epam.admissioncommission.enteties.ApplicationMark;

public class ApplicationMarkDao implements IApplicationMarkDao {
	public static final String APPLICATION_ID = "application_id";
	public static final String MARK_ID = "mark_id";
	
	private static final String QUERY_FOR_DELETE = "DELETE FROM application_mark WHERE application_id = ? AND mark_id = ?";
	private static final String QUERY_FOR_UPDATE_BY_APPLICATION_ID = "UPDATE application_mark SET mark_id = ? "
			+ "WHERE application_id = ?";
	private static final String QUERY_FOR_UPDATE_BY_MARK_ID = "UPDATE application_mark SET application_id = ?"
			+ "WHERE mark_id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO application_mark(application_id, mark_id) VALUES(?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM application_mark";
	private static final String QUERY_FOR_FIND_BY_APPLICATION_ID = "SELECT * FROM application_mark WHERE application_id = ";
	private static final String QUERY_FOR_FIND_BY_MARK_ID = "SELECT * FROM application_mark WHERE mark_id = ";
	
	@Override
	public void delete(int applicationId, int markId) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE);){
			statement.setInt(1, applicationId);
			statement.setInt(2, markId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
		}
	}

	@Override
	public List<ApplicationMark> selectAll() {
		List<ApplicationMark> applicationMarks = new ArrayList<ApplicationMark>();
		ApplicationMark currentMark = new ApplicationMark();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				currentMark.setApplicationId(result.getInt(APPLICATION_ID));
				currentMark.setMarkId(result.getInt(MARK_ID));
				applicationMarks.add(currentMark);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applicationMarks;
	}

	@Override
	public List<ApplicationMark> findByApplicationId(int applicationId) {
		List<ApplicationMark> applicationMarks = new ArrayList<ApplicationMark>();
		ApplicationMark currentMark;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_APPLICATION_ID + applicationId)) {
			while(result.next()){
				currentMark = new ApplicationMark();
				currentMark.setApplicationId(result.getInt(APPLICATION_ID));
				currentMark.setMarkId(result.getInt(MARK_ID));
				applicationMarks.add(currentMark);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applicationMarks;
	}

	@Override
	public List<ApplicationMark> findbyMarkid(int markId) {
		List<ApplicationMark> applicationMarks = new ArrayList<ApplicationMark>();
		ApplicationMark currentMark = new ApplicationMark();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_MARK_ID +  markId)) {
			while(result.next()){
				currentMark.setApplicationId(result.getInt(APPLICATION_ID));
				currentMark.setMarkId(result.getInt(MARK_ID));
				applicationMarks.add(currentMark);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applicationMarks;
	}

}
