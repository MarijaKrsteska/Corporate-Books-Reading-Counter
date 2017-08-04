package com.epam.library.dao.impl;
import java.sql.*;
import com.epam.library.CorporateBooksReadingCounter;
import com.epam.library.bean.Book;
import com.epam.library.dao.BookDAO;
import com.epam.library.dao.SQLCommand;

/** The class implements the interface {@link BookDAO}*/
public class BookDAOImpl implements BookDAO{
	private static PreparedStatement preparedStatement = null;
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	
	public void addBook(String title, String brief, int publishYear, String authorName) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.INSERT_BOOK);
		
		preparedStatement.setString(1, title);
		preparedStatement.setString(2, brief);
		preparedStatement.setInt(3, publishYear);
		preparedStatement.setString(4, authorName);
		
		preparedStatement.executeUpdate();
	}
	
	public static Book getBookByID(int bookID) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.SELECT_BOOK_WHERE_ID);
		preparedStatement.setInt(1, bookID);
		
		resultSet = preparedStatement.executeQuery();
		
		Book book = new Book(resultSet.getInt("BOOK_ID"), resultSet.getString("TITLE"), 
				resultSet.getString("BRIEF"), resultSet.getInt(4), resultSet.getString(5));

		return book;
	}
	
	public void deleteBookByID(int bookID) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.DELETE_BOOK_WHERE_ID);
		preparedStatement.setInt(1, bookID);
		
		preparedStatement.executeUpdate();
		
		connection.close();
		preparedStatement.close();
	}
	
	
	public void renameBook(String oldTitle, String newTitle) throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.RENAME_BOOK);
		
		preparedStatement.setString(1, newTitle);
		preparedStatement.setString(2, oldTitle);

		preparedStatement.executeUpdate();
	}
	
	public int numOfBooks() throws SQLException, ClassNotFoundException{
		CorporateBooksReadingCounter.initializeConnection();

		preparedStatement = connection.prepareStatement(SQLCommand.COUNT_BOOKS);
		
		resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getInt(1);
	}
}
