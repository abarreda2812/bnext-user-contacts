package com.orangebank.delivery.preparator.mocks;

import org.springframework.dao.DataAccessException;

public class TestExceptionDB extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4375234518019494268L;

	public TestExceptionDB(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TestExceptionDB(Throwable cause) {
		super("Test Error", cause);
	}
	
	public TestExceptionDB(String msg) {
		super("Test Error", null);
	}

}
