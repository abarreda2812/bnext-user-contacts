package com.orangebank.delivery.preparator.controller;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import org.springframework.http.ResponseEntity;

/**
 * Controlador de preparación de pedidos. Se encarga de registrar los pedidos de
 * uno en uno y posteriormente los envía para su procesado.
 * 
 * @author abarreda
 *
 */
public interface IDeliveryPreparatorController {
	public ResponseEntity<Void> prepareDelivery(PackageRequestDto packageRequestDto);
}
