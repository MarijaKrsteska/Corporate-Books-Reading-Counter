package com.epam.library.dao.exception;

public class MySQLException extends Exception {
	public MySQLException(Exception e) {
		super(e);
	}
}
