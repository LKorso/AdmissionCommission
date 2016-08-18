package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IApplicationDao;
import com.admissioncommission.dao.creators.QueryBuilder;
import com.admissioncommission.enteties.Application;

public class ApplicationDao implements IApplicationDao {
	private static final String ID = "id";
	private static final String FILLING_DATE = "filling_date";
	private static final String APPLICANT_ID = "applicant_id";
	private static final String FACULTY_ID = "faculty_id";
	private static final String STATUS_ID = "status_id";
	private static final String DESCRIPTION = "description";
	private static final String TABLE_NAME = "application";
	private static final String PRIORITY_ID = "priority_id";
	private static final String QUERY_FOR_DELETE = "DELETE FROM application WHERE id = ?";
	private static final String QUERY_FOR_UPDATE_DESCRIPTION = "UPDATE application SET description = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO application(filling_date, applicant_id, faculty_id, status_id, priority_id) VALUES (?,?,?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM application";
	private static final String QUERY_FOR_FIND_BY_ID = "SLECT * FROM application WHERE id = ";
	private static final String QUERY_FOR_FIND_BY_APPLICANT_ID = "SELECT * FROM application WHERE applicant_id = ";
	private static final String QUERY_FOR_FIND_BY_FACULTY_ID = "SELECT * FROM application WHERE faculty_id = ";
	private static final String QUERY_FOR_FIND_BY_STATUS_ID = "SELECT * FROM application WHERE status_id = ";
	
	private static final Logger LOGGER = LogManager.getLogger(ApplicationDao.class);
	
	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing applciation", exception);
		}
	}

	@Override
	public void update(HashMap<String, String> changes, HashMap<String, String> criterions) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(getQueryForUpdate(changes, criterions));) {
			statement.executeUpdate();
		} catch(SQLException exception) {
			LOGGER.error("Error updating application", exception);
		}
	}
	
	@Override
	public void notEqualUpdate(String field, String newValue, String criterion) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(getQueryForNotEqualUpdate(field, newValue, criterion));) {
			statement.executeUpdate();
		} catch(SQLException exception) {
			LOGGER.error("Error updating application", exception);
		}
	}
	
	@Override
	public void updateDescription(int id, String description){
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE_DESCRIPTION);) {
			statement.setString(1, description);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch(SQLException exception) {
			LOGGER.error("Error updating application's description", exception);
		}
	}
	
	@Override
	public void insert(Application newApplication) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setDate(1, newApplication.getDate());
			statement.setInt(2, newApplication.getApplicantId());
			statement.setInt(3, newApplication.getFacultyId());
			statement.setInt(4, newApplication.getStatusId());
			statement.setInt(5, newApplication.getPriorityId());
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error inserting into table ", exception);
		}
	}

	@Override
	public List<Application> selectAll() {
		List<Application> applications = new ArrayList<Application>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				applications.add(setApplication(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
		}
		
		return applications;
	}

	@Override
	public Application findbyId(int id) {
		Application currentApplication = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentApplication = setApplication(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by id", exception);
		}
		
		return currentApplication;
	}

	@Override
	public List<Application> findByApplicantId(int applicantId) {
		List<Application> applications = new ArrayList<Application>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_APPLICANT_ID + applicantId)) {
			while(result.next()){
				applications.add(setApplication(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by applicant id", exception);
		}
		
		return applications;
	}

	@Override
	public List<Application> findByFacultyId(int facultyId) {
		List<Application> applications = new ArrayList<Application>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_FACULTY_ID + facultyId)) {
			while(result.next()){
				applications.add(setApplication(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by faculty id", exception);
		}
		
		return applications;
	}

	@Override
	public List<Application> findByStatusID(int statusId) {
		List<Application> applications = new ArrayList<Application>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_STATUS_ID + statusId)) {
			while(result.next()){
				applications.add(setApplication(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by status id", exception);
		}
		
		return applications;
	}
	
	public List<Application> customSelect(HashMap<String, String> criterions){
		List<Application> applications = new ArrayList<Application>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(createCustomQuery(criterions))) {
			while(result.next()){
				applications.add(setApplication(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error custom selecting from table", exception);
		}
		
		return applications;
	}
	
	private Application setApplication(ResultSet result) throws SQLException{
		Application application = new Application();
		
		application.setId(result.getInt(ID));
		application.setDate(result.getDate(FILLING_DATE));
		application.setApplicantId(result.getInt(APPLICANT_ID));
		application.setFacultyId(result.getInt(FACULTY_ID));
		application.setStatusId(result.getInt(STATUS_ID));
		application.setDescription(result.getString(DESCRIPTION));
		application.setPriorityId(result.getInt(PRIORITY_ID));
		
		return application;
	}
	
	private String createCustomQuery(HashMap<String, String> criterions){
		QueryBuilder queryBuilder = new QueryBuilder(QueryBuilder.SELECT, TABLE_NAME); 
		queryBuilder.addCriterion(criterions);
		return queryBuilder.toString();
	}
	
	private String getQueryForUpdate(HashMap<String, String> changes, HashMap<String, String> criterions){
		QueryBuilder builder = new QueryBuilder(QueryBuilder.UPDATE, TABLE_NAME);
		builder.addChanges(changes);
		builder.addCriterion(criterions);
		return builder.toString();
	}
	
	private String getQueryForNotEqualUpdate(String field, String newValue, String criterion){
		QueryBuilder builder = new QueryBuilder(QueryBuilder.UPDATE, TABLE_NAME);
		builder.addChanges(field, newValue);
		builder.addNotEqual(field, criterion);
		return builder.toString();
	}
}
