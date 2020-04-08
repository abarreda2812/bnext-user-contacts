package com.orangebank.delivery.preparator.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangebank.delivery.preparator.controller.IDeliveryPreparatorController;
import com.orangebank.delivery.preparator.dtos.NotificationDto;
import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import com.orangebank.delivery.preparator.service.ISenderDeliveryProcessor;
import com.orangebank.delivery.preparator.validator.PackageRequestDtoValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementación del controlador de preparación de pedidos. Se encarga de
 * registrar los pedidos de uno en uno y posteriormente los envía para su
 * procesado.
 * 
 * @author abarreda
 *
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}")
@Api(tags = {
		"DeliveryPreparatorController" }, description = "Controlador de preparación de pedidos. Se encarga de registrar los pedidos de uno en uno y posteriormente los envía para su procesado.")
@Slf4j
public class DeliveryPreparatorController implements IDeliveryPreparatorController {

	@Autowired
	private ISenderDeliveryProcessor  senderDeliveryProcessor;
	
	@Override
	@PostMapping()
	@ApiOperation(value = "Registra un pedido en el sistema.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pedido procesado de manera correcta."),
			@ApiResponse(code = 400, message = "Los parámetros del pedido no son correctos.", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = NotificationDto.class) })
	public ResponseEntity<Void> prepareDelivery(@Validated(PackageRequestDtoValidator.class) @RequestBody PackageRequestDto packageRequestDto) {
		log.info("@prepareDelivery  entrando en preparación de pedido.");
		senderDeliveryProcessor.sendDeliveryForProcessor(packageRequestDto);
		log.info("@prepareDelivery  pedido procesado.");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
