package com.epam.library.dao;
import java.sql.Date;
import java.sql.SQLException;
import com.epam.library.bean.Employee;

/** Interface employee DAO*/
public interface EmployeeDAO {
	public void addEmployee(String name, Date dateOfBirth, String email) throws SQLException, ClassNotFoundException;
	public Employee getEmployeeByID(int employeeID) throws SQLException, ClassNotFoundException;
	public void deleteEmployeeByID(int empoloyeeID) throws SQLException, ClassNotFoundException;
	public int numOfEmployees() throws SQLException, ClassNotFoundException;
}
