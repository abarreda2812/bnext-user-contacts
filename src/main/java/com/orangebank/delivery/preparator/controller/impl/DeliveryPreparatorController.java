package com.orangebank.delivery.preparator.controller.impl;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
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
import io.swagger.annotations.ApiParam;
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
@Validated
@Api(tags = {
		"DeliveryPreparatorController" }, description = "Controlador de preparación de pedidos. Se encarga de registrar los pedidos de uno en uno y posteriormente los envía para su procesado.")
@Slf4j
public class DeliveryPreparatorController implements IDeliveryPreparatorController {

	private ISenderDeliveryProcessor senderDeliveryProcessor;
	private PackageRequestDtoValidator validator;

	@Autowired
	public DeliveryPreparatorController(ISenderDeliveryProcessor senderDeliveryProcessor,
			PackageRequestDtoValidator validator) {
		this.senderDeliveryProcessor = senderDeliveryProcessor;
		this.validator = validator;
	}

	@Override
	@PostMapping()
	@ApiOperation(value = "Registra un pedido en el sistema.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pedido procesado de manera correcta."),
			@ApiResponse(code = 400, message = "Los parámetros del pedido no son correctos.", response = NotificationDto.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = NotificationDto.class) })
	public ResponseEntity<NotificationDto> prepareDelivery(
			@ApiParam(value = "Objeto de petición de envíos.", required = true) @Valid @RequestBody PackageRequestDto packageRequestDto) {
		log.info("@prepareDelivery  entrando en preparación de pedido.");
		Errors errors = new BeanPropertyBindingResult(packageRequestDto, "packageRequestDto");
		validator.validate(packageRequestDto, errors);
		if (errors.hasFieldErrors()) {
			NotificationDto notification = NotificationDto.builder().status(HttpStatus.BAD_REQUEST.toString())
					.message("Se han producido errores de validación en el objeto")
					.errorCode(errors.getAllErrors().stream().map(e -> e.getCode()).collect(Collectors.joining(";")))
					.build();

			return new ResponseEntity<>(notification, HttpStatus.BAD_REQUEST);
		}
		senderDeliveryProcessor.sendDeliveryForProcessor(packageRequestDto);
		log.info("@prepareDelivery  pedido procesado.");
		return new ResponseEntity<>(NotificationDto.builder().status(HttpStatus.CREATED.toString())
				.message("El paquete ha sido procesado para su posterior envío").build(), HttpStatus.CREATED);
	}

}
