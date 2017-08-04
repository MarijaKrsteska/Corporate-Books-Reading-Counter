package com.epam.library.dao.exception;

public class MyClassNotFoundException extends Exception {
	public MyClassNotFoundException(Exception e) {
		super(e);
	}
}
