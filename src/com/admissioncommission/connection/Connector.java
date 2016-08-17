package com.admissioncommission.connection;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;

public class Connector {
	
	private static final Logger LOGGER = LogManager.getLogger(Connector.class);
	
	public static Connection getConnection() throws SQLException {
		DataSource dataSource = null;
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/connection");
		} catch (NamingException exception) {
			LOGGER.error("Error getting data source", exception);
		}
		return dataSource.getConnection();
	}
}
