package com.admissioncommission.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class Connector {
	private static final String RESOURCE_FILE = "com.admissioncommission.connection.database";
	private static ResourceBundle property;
	private static String url;
	private static String user;
	private static String pass; 
	private static Connection connection;
	
	private static final Logger LOGGER = LogManager.getLogger(Connector.class);
	
	public static Connection getConnection() throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException exception){
			LOGGER.error("Error getting connection", exception);
		} catch (InstantiationException exception) {
			LOGGER.error("Error getting connection", exception);
		} catch (IllegalAccessException exception) {
			LOGGER.error("Error getting connection", exception);
		}
		
		property = ResourceBundle.getBundle(RESOURCE_FILE);
		url = property.getString("url");
		user = property.getString("user");
		pass  = property.getString("password");
		connection = (Connection) DriverManager.getConnection(url, user, pass);
		
		return connection;
	}
}
