package com.admc.dao.implementation.mysql;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.admc.connection.Connector;
import com.admc.dao.IUserDao;
import com.admc.dao.creators.QueryBuilder;
import com.admc.enteties.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserDao implements IUserDao {
	private static final String ID = "id";
	private static final String LAST_NAME = "last_name";
	private static final String FIRST_NAME = "first_name";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String PASSWORD = "password"; 
	private static final String FACULTY_ID = "faculty_id";
	private static final String USER_ROLE = "role";
	private static final String TABLE_NAME = "user";
	private static final String QUERY_FOR_DELETE = "DELETE FROM user WHERE id = ?";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM user";

	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM user WHERE id = ";
	private static final Logger LOGGER = LogManager.getLogger(UserDao.class);

	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE);) {
			statement.setInt(1, id);
			statement.executeUpdate();
			throw new SQLException();
		} catch(SQLException exception) {
			LOGGER.error("Error deleteing user", exception);
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
			LOGGER.error("Error updating mark", exception);
		}
	}

	@Override
	public void insert(User newUser) {
		try(Connection connection = Connector.getConnection();
			PreparedStatement statement = connection.prepareStatement(getCurrentUserInsertQuery(newUser.getRole()))) {
			statement.setString(1, newUser.getLastName());
			statement.setString(2, newUser.getFirstName());
			statement.setTimestamp(3, toTimestamp(newUser.getDateOfBirth()));
			statement.setString(4, newUser.getSex());
			statement.setString(5, newUser.getEmail());
			statement.setString(6,  newUser.getPhone());
			statement.setString(7, newUser.getRole().toString());
			statement.setString(8, newUser.getPassword());
			if(newUser.getRole().equals(User.UserRole.STUDENT)){
				statement.setInt(9, newUser.getFacultyId());
			}
			statement.executeUpdate();
		} catch(SQLException exception){
			LOGGER.error("Error inserting into table ", exception);
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
			LOGGER.error("Error updating mark", exception);
		}
	}

	@Override
	public List<User> selectAll() {
		List<User> users = new ArrayList<>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			users.add(setUser(result));
		} catch(SQLException exception){
			LOGGER.error("Error selecting from table", exception);
		}
		return users;
	}

	@Override
	public List<User> selectSpecificUsers(User.UserRole role) {
		List<User> users = new ArrayList<>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(getSelectSpecificUserQuery(role))) {
			users.add(setUser(result));
		} catch(SQLException exception){
			LOGGER.error("Error selecting specific user", exception);
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
			LOGGER.error("Error selecting from table by id", exception);
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
			LOGGER.error("Error selecting from table by email and password", exception);
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
	
	private String getSelectSpecificUserQuery(User.UserRole role){
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM user WHERE role = ");
		queryBuilder.append(role);
		
		return queryBuilder.toString();
	}
	
 	private String getCurrentUserInsertQuery(User.UserRole role){
		StringBuilder queryBuilder = new StringBuilder("INSERT INTO user(last_name, first_name, date_of_birth, sex, email, phone,"
												+ "role, password");
		
		if(role.equals(User.UserRole.STUDENT)){
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
		currentUser.setDateOfBirth(toLocaleDateTime(result.getTimestamp(DATE_OF_BIRTH)));
		currentUser.setEmail(result.getString(EMAIL));
		currentUser.setPhone(result.getString(PHONE));
		currentUser.setSex(result.getString(SEX));
		currentUser.setRole(createUserRole(result.getString(USER_ROLE)));
		currentUser.setFacultyId(result.getInt(FACULTY_ID));
		return currentUser;
	}

	private LocalDateTime toLocaleDateTime(Timestamp date) {
		return (date == null ? null : date.toLocalDateTime());
	}

	private Timestamp toTimestamp(LocalDateTime date) {
		return (date == null ? null : Timestamp.valueOf(date));
	}

	private User.UserRole createUserRole(String roleName) {
		User.UserRole currentRole = null;
 		for (User.UserRole role : User.UserRole.values()) {
			if (role.toString().equals(roleName)) {
				currentRole = role;
				break;
			}
		}
		return currentRole;
	}
}
