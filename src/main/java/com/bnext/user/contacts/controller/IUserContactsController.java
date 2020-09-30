package com.orangebank.delivery.preparator.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.orangebank.delivery.preparator.dtos.NotificationDto;
import com.orangebank.delivery.preparator.dtos.PackageRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controlador de preparación de pedidos. Se encarga de registrar los pedidos de
 * uno en uno y posteriormente los envía para su procesado.
 * 
 * @author abarreda
 *
 */
@Api(tags = {
"DeliveryPreparatorController" }, description = "Controlador de preparación de pedidos. Se encarga de registrar los pedidos de uno en uno y posteriormente los envía para su procesado.")
public interface IDeliveryPreparatorController {
	@ApiOperation(value = "Registra un pedido en el sistema.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pedido procesado de manera correcta."),
			@ApiResponse(code = 400, message = "Los parámetros del pedido no son correctos.", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = NotificationDto.class) })
	public ResponseEntity<NotificationDto> prepareDelivery(@Valid PackageRequestDto packageRequestDto);
}
