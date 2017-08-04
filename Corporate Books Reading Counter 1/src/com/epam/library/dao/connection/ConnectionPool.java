package com.epam.library.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool{
	private static final String driver = "org.gjt.mm.mysql.Driver";
	private static final String user = "root";
	private static final String password = "1234";
	private static final String url = "jdbc:mysql://127.0.0.1/Database?useSSL=false";
	
	/** This constructor serves to initialize the connection parameters to the database. 
	 * @throws ClassNotFoundException 
	 * @throws SQLException */
	private ConnectionPool() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection connection =  DriverManager.getConnection(url, user, password);
	}
}
