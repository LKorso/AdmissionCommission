package com.admc.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


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