package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IApplicationDao;
import com.admissioncommission.dao.creators.QueryBuilder;
import com.admissioncommission.enteties.Application;

public class ApplicationDao implements IApplicationDao {
	public static final String ID = "id";
	public static final String FILLING_DATE = "filling_date";
	public static final String APPLICANT_ID = "applicant_id";
	public static final String FACULTY_ID = "faculty_id";
	public static final String STATUS_ID = "status_id";
	public static final String DESCRIPTION = "description";
	
	private static final String TABLE_NAME = "application";
	
	private static final String QUERY_FOR_DELETE = "DELETE FROM application WHERE id = ?";
	private static final String QUERY_FOR_UPDATE_DESCRIPTION = "UPDATE application SET description = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO application(filling_date, applicant_id, faculty_id, status_id) VALUES (?,?,?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM application";
	private static final String QUERY_FOR_FIND_BY_ID = "SLECT * FROM application WHERE id = ";
	private static final String QUERY_FOR_FIND_BY_APPLICANT_ID = "SLECT * FROM application WHERE applicant_id = ";
	private static final String QUERY_FOR_FIND_BY_FACULTY_ID = "SLECT * FROM application WHERE faculty_id = ";
	private static final String QUERY_FOR_FIND_BY_STATUS_ID = "SLECT * FROM application WHERE status_id = ";
	
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
	public void update(HashMap<String, String> changes, HashMap<String, String> criterions) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(getQueryForUpdate(changes, criterions));) {
			statement.executeUpdate();
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void notEqualUpdate(String field, String newValue, String criterion) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(getQueryForNotEqualUpdate(field, newValue, criterion));) {
			statement.executeUpdate();
		} catch(SQLException exception) {
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
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
			exception.printStackTrace();
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
