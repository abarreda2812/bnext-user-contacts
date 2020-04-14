package com.orangebank.delivery.preparator.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.orangebank.delivery.preparator.dtos.NotificationDto;
import com.orangebank.delivery.preparator.dtos.PackageRequestDto;

/**
 * Controlador de preparación de pedidos. Se encarga de registrar los pedidos de
 * uno en uno y posteriormente los envía para su procesado.
 * 
 * @author abarreda
 *
 */
public interface IDeliveryPreparatorController {
	public ResponseEntity<NotificationDto> prepareDelivery(@Valid PackageRequestDto packageRequestDto);
}
