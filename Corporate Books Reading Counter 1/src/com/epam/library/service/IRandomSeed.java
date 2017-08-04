package com.epam.library.service;

import java.sql.SQLException;
import java.util.List;
import com.epam.library.bean.*;

public interface IRandomSeed {
	public void addPairs(List<Employee_Book> pairs, int bookCount, int numOfEmployees, int numOfBooks);
	public List<Employee_Book> generatePairs() throws SQLException, ClassNotFoundException;
}
