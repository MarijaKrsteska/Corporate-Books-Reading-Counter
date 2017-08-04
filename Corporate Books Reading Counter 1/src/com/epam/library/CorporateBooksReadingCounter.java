package com.epam.library;
import java.sql.*;

public class CorporateBooksReadingCounter {
	private static final String driver = "org.gjt.mm.mysql.Driver";
	private static final String user = "root";
	private static final String password = "1234";
	private static final String url = "jdbc:mysql://127.0.0.1/Database?useSSL=false";

	public static void initializeConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection connection =  DriverManager.getConnection(url, user, password);
	}
}
