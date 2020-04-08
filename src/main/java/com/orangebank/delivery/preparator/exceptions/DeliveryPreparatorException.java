package com.orangebank.delivery.preparator.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryPreparatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String ERRORCODE;

	public DeliveryPreparatorException(String message, String ERRORCODE) {
		super(message);
		this.ERRORCODE = ERRORCODE;
	}

}
