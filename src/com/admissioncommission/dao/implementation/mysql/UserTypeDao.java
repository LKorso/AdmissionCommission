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
import com.admissioncommission.dao.IUserTypeDao;
import com.admissioncommission.enteties.UserType;

public class UserTypeDao implements IUserTypeDao {
	private static final String ID = "id";
	private static final String TYPE = "type";
	private static final String QUERY_FOR_DELETE = "DELETE FROM user_type WHERE id = ?";
	private static final String QUERY_FOR_UPDATE = "UPDATE user_type SET name = ? WHERE id = ?";
	private static final String QUERY_FOR_INSERT = "INSERT INTO user_type(id, type) VALUES (?,?)";
	private static final String QUERY_FOR_SELECT_ALL = "SELECT * FROM user_type";
	private static final String QUERY_FOR_FIND_BY_ID = "SELECT * FROM user_type WHERE id = ";
	private static final String QUERY_FOR_FIND_BY_TYPE = "SELECT * FROM user_type WHERE type = \"";

	private static final Logger LOGGER = LogManager.getLogger(UserTypeDao.class);
	
	@Override
	public void delete(int id) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error deleteing user type", exception);
		}
	}

	@Override
	public void update(int id, String newType) {
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {
			statement.setString(1, newType);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error updatine user type", exception);
		}
	}

	@Override
	public void insert(UserType newType) {
		try (Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_FOR_INSERT)) {
			statement.setInt(1, newType.getId());
			statement.setString(2, newType.getType());
			statement.executeUpdate();
		} catch (SQLException exception) {
			LOGGER.error("Error inserting into table ", exception);
		}
	}

	@Override
	public List<UserType> selectAll() {
		List<UserType> userTypes = new ArrayList<UserType>();
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_SELECT_ALL)) {
			while(result.next()){
				userTypes.add(setUserType(result));
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table", exception);
		}
		
		return userTypes;
	}

	@Override
	public UserType findById(int id) {
		UserType currentUserType = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_ID + id)) {
			while(result.next()){
				currentUserType = setUserType(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by id", exception);
		}
		
		return currentUserType;
	}

	@Override
	public UserType findByType(String type) {
		UserType currentUserType = null;
		
		try(Connection connection = Connector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(QUERY_FOR_FIND_BY_TYPE + type + "\"")) {
			while(result.next()){
				currentUserType = setUserType(result);
			}
		} catch (SQLException exception) {
			LOGGER.error("Error selecting from table by type", exception);
		}
		
		return currentUserType;
	}
	
	private UserType setUserType(ResultSet result) throws SQLException{
		UserType currentUserType = new UserType();
		currentUserType.setId(result.getInt(ID));
		currentUserType.setType(result.getString(TYPE));
		return currentUserType;
	}
}
