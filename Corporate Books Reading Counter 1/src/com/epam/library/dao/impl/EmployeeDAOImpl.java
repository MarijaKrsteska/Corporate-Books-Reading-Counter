package com.epam.library.dao.impl;
import java.sql.*;
import com.epam.library.CorporateBooksReadingCounter;
import com.epam.library.bean.Employee;
import com.epam.library.dao.EmployeeDAO;
import com.epam.library.dao.SQLCommand;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	
	public void addEmployee(String name, Date dateOfBirth, String email) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();
		
		preparedStatement = connection.prepareStatement(SQLCommand.INSERT_EMPLOYEE);
		
		preparedStatement.setString(1, name);
		preparedStatement.setDate(2, dateOfBirth);
		preparedStatement.setString(3, email);
		
		preparedStatement.executeUpdate();
	}
	
	public Employee getEmployeeByID(int employeeID) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();
		
		preparedStatement = connection.prepareStatement(SQLCommand.SELECT_EMPLOYEE_WHERE_ID);
		preparedStatement.setInt(1, employeeID);
		
		resultSet = preparedStatement.executeQuery();
		
		Employee employee = new Employee(resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("NAME"), 
				resultSet.getDate("DATE_OF_BIRTH"), resultSet.getString("EMAIL"));
	
		return employee;
	}
	
	public void deleteEmployeeByID(int empoloyeeID) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();
		
		preparedStatement = connection.prepareStatement(SQLCommand.DELETE_EMPLOYEE_WHERE_ID);
		preparedStatement.setInt(1, empoloyeeID);
		
		preparedStatement.executeUpdate();
	}
	
	public int numOfEmployees() throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();
		
		preparedStatement = connection.prepareStatement(SQLCommand.COUNT_EMPLOYEES);
		
		resultSet = preparedStatement.executeQuery();
		resultSet.next();

		return resultSet.getInt(1);
	}
}
