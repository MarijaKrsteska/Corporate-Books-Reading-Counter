package com.epam.library.dao.impl;
import java.sql.*; 
import java.util.*;
import com.epam.library.CorporateBooksReadingCounter;
import com.epam.library.bean.*;
import com.epam.library.dao.*;
import com.epam.library.service.impl.RandomSeed;

/** The class implements the interface {@link EmployeeBookDAO}*/
public class EmployeeBookDAOImpl implements EmployeeBookDAO {
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	
	private static RandomSeed rs = new RandomSeed();
	
	public void addEmployeeBook() throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		List<Employee_Book> pairs = rs.generatePairs();
		for (Employee_Book pair : pairs) {
			preparedStatement = connection.prepareStatement(SQLCommand.INSERT_EMPLOYEE_BOOK);
			
			preparedStatement.setInt(1, pair.getEmployeeID());
			preparedStatement.setInt(2, pair.getBookID());
			
			preparedStatement.executeUpdate();
		}
	}
	
	
	public void printEmployeesWhoHaveReadMoreThanOneBook() throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.EMPLOYEES_WHO_HAVE_READ_MORE_THAN_ONE_BOOK);
		
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			int numberOfBooks = resultSet.getInt(1);
			int employeeID= resultSet.getInt(2);
			
			EmployeeDAOImpl empl = new EmployeeDAOImpl();
			Employee employee =  empl.getEmployeeByID(employeeID);
			
			System.out.println(String.format("Employee %s - Number of books: %d" ,employee.getName(), numberOfBooks));
		}
	}
	
	public void printEmployeesWhoHaveReadMoreThanOrEqualToTwoBooks() throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.EMPLOYEES_WHO_HAVE_READ_MORE_THAN_OR_EQUAL_TO_TWO_BOOKS);
		
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			int numberOfBooks = resultSet.getInt(1);
			int employeeID= resultSet.getInt(2);			

			EmployeeDAOImpl empl = new EmployeeDAOImpl();
			Employee employee =  empl.getEmployeeByID(employeeID);
			
			System.out.println(String.format("Employee %s %s - Number of books: %d" ,employee.getName(), employee.getDateOfBirth(), numberOfBooks));
		}
	}
}
