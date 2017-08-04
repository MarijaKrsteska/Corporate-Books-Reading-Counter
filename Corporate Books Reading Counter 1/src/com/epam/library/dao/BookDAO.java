package com.epam.library.dao;
import java.sql.SQLException;

/** Interface book DAO*/
public interface BookDAO {
	public void addBook(String title, String brief, int publishYear, String authorName) throws SQLException, ClassNotFoundException;
	public void deleteBookByID(int bookID) throws SQLException, ClassNotFoundException;
	public void renameBook(String oldTitle, String newTitle) throws SQLException, ClassNotFoundException;
	public int numOfBooks() throws SQLException, ClassNotFoundException;
}
