package com.admissioncommission.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

public class Connector {
	private static final String RESOURCE_FILE = "resources/database.properties";
	private static Properties property;
	private static String url;
	private static String user;
	private static String pass; 
	private static Connection connection;
	
	/*public static Connection getConnection() throws SQLException {
		try(FileInputStream stream = new FileInputStream(new File(RESOURCE_FILE))){
			property = new Properties();
			property.load(stream);
			url = property.getProperty("url");
			user = property.getProperty("user");
			pass  = property.getProperty("password");
		} catch (FileNotFoundException exception){
			exception.printStackTrace();
		} catch (IOException exception){
			exception.printStackTrace();
		} 
		
		connection = (Connection) DriverManager.getConnection(url, user, pass);
		
		return connection;
	}*/
	public static Connection getConnection() throws SQLException {
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			url = "jdbc:mysql://localhost:3306/admission_commision?useUnicode=true";
			user = "root";
			pass  = "root";
	
		
		connection = (Connection) DriverManager.getConnection(url, user, pass);
		
		return connection;
	}
}
