package com.bnext.user.contacts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;
import lombok.Setter;

/**
 * General Exception of app.
 * @author abarreda
 *
 */
@Getter
@Setter
public class UserContactsException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
 	 * @param reason exception message.
	 */
	public UserContactsException(final String reason) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
	}
	
	/**
	 * Constructor.
	 * @param status HttpStatus.
 	 * @param reason exception message.
	 */
	public UserContactsException(final HttpStatus status, final String reason) {
		super(status, reason);
	}

}
