package com.orangebank.delivery.preparator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orangebank.delivery.preparator.dtos.NotificationDto;

/**
 * Manejador de excepciones para los posibles errores al enviar por mensajería.
 * @author abarreda
 *
 */
@RestControllerAdvice
public class RestExceptionHandler{

	@ExceptionHandler(DeliveryPreparatorException.class)
	public ResponseEntity<NotificationDto> handleExpirationRefeshNotFound(DeliveryPreparatorException ex) {

		NotificationDto notification = NotificationDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
				.message(ex.getMessage()).errorCode(ex.getERRORCODE()).build();

		return new ResponseEntity<>(notification, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
