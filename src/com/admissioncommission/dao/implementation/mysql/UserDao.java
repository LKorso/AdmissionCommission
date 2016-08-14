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
import com.admissioncommission.dao.IUserDao;
import com.admissioncommission.dao.creators.QueryBuilder;
import com.admissioncommission.enteties.User;

public class UserDao implements IUserDao {
	private static final String ID = "id";
	private static final String LAST_NAME = "last_name";
	private static final String FIRST_NAME = "first_name";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String USER_TYPE_ID = "user_type_id";
	private static final String PASSWORD = "password"; 
	private static final String FACULTY_ID = "faculty_id";
	private static final String TABLE_NAME = "user";
	
	private static final String USER_TYPE_ADMINISTRATOR = "Administrator";
	private static final String USER_TYPE_APPLICANT = "Applicant";
	private static final String USER_TYPE_STUDENT = "Student";
	
	private static final String QUERY_FOR_DELETE = "DELETE FROM user WHERE id = ?";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM user";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM user WHERE id = ";

	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE);) {
			statement.setInt(1, id);
			statement.executeUpdate();
			throw new SQLException();
		} catch(SQLException exception) {
			
		}
	}

	@Override
	public void update(HashMap<String, String> changes, HashMap<String, String> criterions){
		QueryBuilder queryBuilder = new QueryBuilder(QueryBuilder.UPDATE, TABLE_NAME);
		queryBuilder.addChanges(changes);
		queryBuilder.addCriterion(criterions);
		
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());) {
			statement.executeUpdate();
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	public void update(String newValue, int id, String fieldName) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(getUpdateQuery(fieldName));) {
			statement.setString(1, fieldName);
			statement.setString(2, newValue);
			statement.setInt(3, id);
			statement.executeUpdate();
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void insert(User newUser, String userType) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(getCurrentUserInsertQuery(userType))) {
				statement.setString(1, newUser.getLastName());
				statement.setString(2, newUser.getFirstName());
				statement.setDate(3, newUser.getDateOfBirth());
				statement.setString(4, newUser.getSex());
				statement.setString(5, newUser.getEmail());
				statement.setString(6,  newUser.getPhone());
				statement.setInt(7, newUser.getUserTypeId());
				statement.setString(8, newUser.getPassword());
				if(userType.equals(USER_TYPE_STUDENT)){
					statement.setInt(9, newUser.getFacultyId());
				}
				statement.executeUpdate();
			} catch(SQLException exception){
				exception.printStackTrace();
			}
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			users.add(setUser(result));
		} catch(SQLException exception){
			
		}
		return users;
	}

	@Override
	public List<User> getSpecificUsers(String userType) {
		List<User> users = new ArrayList<User>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(getSelectSpecificUserQuery(userType))) {
			users.add(setUser(result));
		} catch(SQLException exception){
			
		}
		return users;
	}

	@Override
	public User findById(int id) {
		User currentUser = null;
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentUser = setUser(result);
			}
		} catch(SQLException exception) {
			
		}
		return currentUser;
	}
	
	public User findByEmailPassword(String email, String password){
		User currentUser = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(getFindByEmailPassQuery(email, password))) {
			while(result.next()){
				currentUser = setUser(result);
			}
		} catch(SQLException exception) {
			exception.printStackTrace();
		}
		
		return currentUser;
	} 
	
	private String getFindByEmailPassQuery(String email, String password){
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM user WHERE email = \"");
		queryBuilder.append(email);
		queryBuilder.append("\" AND password = \"");
		queryBuilder.append(password);
		queryBuilder.append("\"");
		
		return queryBuilder.toString();
	}
	
	private String getSelectSpecificUserQuery(String userType){
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM user WHERE user_type_id = ");
		UserTypeDao userTypeDao = new UserTypeDao();
		queryBuilder.append(userTypeDao.findByType(userType).getId());
		
		return queryBuilder.toString();
	}
	
 	private String getCurrentUserInsertQuery(String userType){
		StringBuilder queryBuilder = new StringBuilder("INSERT INTO user(last_name, first_name, date_of_birth, sex, email, phone,"
												+ "user_type_id, password");
		
		if(userType.equals(USER_TYPE_STUDENT)){
			queryBuilder.append(", faculty_id) VALUES(?,?,?,?,?,?,?,?,?)");
		} else {
			queryBuilder.append(") VALUES(?,?,?,?,?,?,?,?)");
		}
		
		return queryBuilder.toString();
	}
	
	private String getUpdateQuery(String fieldName) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("UPDATE user SET ");
		builder.append(fieldName);
		builder.append(" = ? WHERE id = ?");
		
		return builder.toString();
	}
	
	private User setUser(ResultSet result) throws SQLException{
		User currentUser = new User();
		currentUser.setId(result.getInt(ID));
		currentUser.setLastName(result.getString(LAST_NAME));
		currentUser.setFirstName(result.getString(FIRST_NAME));
		currentUser.setDateOfBirth(result.getDate(DATE_OF_BIRTH));
		currentUser.setEmail(result.getString(EMAIL));
		currentUser.setPhone(result.getString(PHONE));
		currentUser.setSex(result.getString(SEX));
		currentUser.setUserTypeId(result.getInt(USER_TYPE_ID));
		currentUser.setFacultyId(result.getInt(FACULTY_ID));
		return currentUser;
	}
}
