package com.epam.library.dao;
import java.sql.SQLException;

/** Interface employee_book DAO*/
public interface EmployeeBookDAO {
	public void addEmployeeBook() throws SQLException, ClassNotFoundException;
	public void printEmployeesWhoHaveReadMoreThanOneBook() throws SQLException, ClassNotFoundException;
	public void printEmployeesWhoHaveReadMoreThanOrEqualToTwoBooks() throws SQLException, ClassNotFoundException;
}
