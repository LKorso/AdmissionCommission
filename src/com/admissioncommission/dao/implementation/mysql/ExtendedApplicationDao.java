package com.admissioncommission.dao.implementation.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.admissioncommission.connection.Connector;
import com.admissioncommission.dao.IExtendedApplicationDao;
import com.admissioncommission.dao.creators.QueryBuilder;
import com.admissioncommission.enteties.ExtendedApplication;

public class ExtendedApplicationDao implements IExtendedApplicationDao {
	private static final String TABLE_NAME = "extended_application";
	private static final String USER_ID = "user_id";
	private static final String LAST_NAME = "last_name";
	private static final String FIRST_NAME = "first_name";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String SEX = "sex";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String FACULTY_NAME = "faculty_name";
	private static final String STATUS = "status";
	private static final String FILLING_DATE = "filling_date";
	private static final String DESCRIPTION = "description";
	private static final String APPLICATION_ID = "application_id";
	
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM extended_application";
	private static final String QUERY_FOR_FIND_BY_APPLICANT_ID = "SELECT * FROM extended_application WHERE user_id = ";
	
	@Override
	public List<ExtendedApplication> selectAll() {
		List<ExtendedApplication> applications = new ArrayList<>();
		
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_SELECT_ALL);
				ResultSet result = statement.executeQuery()){
			applications = createEntetiesList(result);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applications;
	}

	@Override
	public List<ExtendedApplication> findByCustomId(int applicantId) {
		List<ExtendedApplication> applications = new ArrayList<>();
		
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_FIND_BY_APPLICANT_ID + applicantId);
				ResultSet result = statement.executeQuery()){
			applications = createEntetiesList(result);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applications;
	}
	
	@Override
	public List<ExtendedApplication> customSelect(HashMap<String,String> criterions) {
		List<ExtendedApplication> applications = new ArrayList<>();
		
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(createCustomQuery(criterions));
				ResultSet result = statement.executeQuery()){
			applications = createEntetiesList(result);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		
		return applications;
	};
	
	private String createCustomQuery(HashMap<String, String> criterions){
		QueryBuilder queryBuilder = new QueryBuilder(QueryBuilder.SELECT, TABLE_NAME); 
		queryBuilder.addCriterion(criterions);
		return queryBuilder.toString();
	}
	
	private List<ExtendedApplication> createEntetiesList(ResultSet result) throws SQLException{
		List<ExtendedApplication> applications = new ArrayList<>();
		ExtendedApplication currentApplication;
		
		while(result.next()){
			currentApplication = new ExtendedApplication();
			currentApplication.setApplicationId(result.getInt(APPLICATION_ID));
			currentApplication.setDateOfBirth(result.getDate(DATE_OF_BIRTH));
			currentApplication.setDescription(result.getString(DESCRIPTION));
			currentApplication.setEmail(result.getString(EMAIL));
			currentApplication.setFaculty(result.getString(FACULTY_NAME));
			currentApplication.setFacultyId(result.getInt(FACULTY_ID));
			currentApplication.setFillingDate(result.getDate(FILLING_DATE));
			currentApplication.setFirstName(result.getString(FIRST_NAME));
			currentApplication.setLastName(result.getString(LAST_NAME));
			currentApplication.setPhone(result.getString(PHONE));
			currentApplication.setSex(result.getString(SEX));
			currentApplication.setStatus(result.getString(STATUS));
			currentApplication.setStatusId(result.getInt(STATUS_ID));
			currentApplication.setUserId(result.getInt(USER_ID));
			applications.add(currentApplication);
		}
		return applications;
	}

}
