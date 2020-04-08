package com.orangebank.delivery.preparator.mocks;

import org.springframework.http.HttpStatus;

import com.orangebank.delivery.preparator.dtos.NotificationDto;

/**
 * Generador de objeto Mock para la clase NotificationDto.
 * 
 * @author abarreda
 * @see NotificationDto
 *
 */
public class NotificationDtoGenerator {

	private final static String STATUS_ERROR_400_ERROR_MESSAGE = "El parámetro introducido es erróneo";
	private final static String STATUS_ERROR_400_ERROR_CODE = "El identificador no sigue el formato XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
	private final static String STATUS_ERROR_500_ERROR_MESSAGE = "Error interno del servidor.";
	private final static String STATUS_ERROR_500_ERROR_CODE = "Java Heap Space.";

	public static NotificationDto generateNotificationDtoSuccess() {
		return NotificationDto.builder().status(HttpStatus.OK.name()).build();
	}

	public static NotificationDto generateNotificationDtoCreated() {
		return NotificationDto.builder().status(HttpStatus.CREATED.name()).build();
	}

	public static NotificationDto generateNotificationDtoInternalServerError() {
		return NotificationDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.message(STATUS_ERROR_500_ERROR_MESSAGE).errorCode(STATUS_ERROR_500_ERROR_CODE).build();
	}
	
	public static NotificationDto generateNotificationDtoBadRequestError() {
		return NotificationDto.builder().status(HttpStatus.BAD_REQUEST.name())
				.message(STATUS_ERROR_400_ERROR_MESSAGE).errorCode(STATUS_ERROR_400_ERROR_CODE).build();
	}
}
