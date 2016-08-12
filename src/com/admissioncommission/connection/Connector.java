package com.admissioncommission.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;

public class Connector {
	private static final String RESOURCE_FILE = "com.admissioncommission.connection.database";
	private static ResourceBundle property;
	private static String url;
	private static String user;
	private static String pass; 
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		property = ResourceBundle.getBundle(RESOURCE_FILE);
		url = property.getString("url");
		user = property.getString("user");
		pass  = property.getString("password");
		connection = (Connection) DriverManager.getConnection(url, user, pass);
		
		return connection;
	}
}
