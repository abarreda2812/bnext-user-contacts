package com.bnext.user.contacts.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.dtos.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

/**
 * API controller for create Users and Contacts.
 * 
 * @author abarreda
 *
 */
@Api(tags = { "UserContactsController" })
public interface IUserContactsController {
	@ApiOperation(value = "Registra un usuario en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Usuario registrado de manera correcta.", response = Mono.class),
			@ApiResponse(code = 400, message = "Los parámetros del usuario no son correctos.", response = Mono.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = Mono.class) })
	public ResponseEntity<Mono<UserDto>> createUser(
			@ApiParam(value = "Objeto de creación de usuario.", required = true) @Valid UserDto userDto);

	@ApiOperation(value = "Registra contactos de un usuario en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Contactos registrados de manera correcta.", response = Mono.class),
			@ApiResponse(code = 400, message = "Los parámetros de entrada no son correctos.", response = Mono.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = Mono.class) })
	public ResponseEntity<Mono<List<UserContactDto>>> createContacts(
			@ApiParam(value = "Objeto de creación de contactos de un usuario.", required = true) @Valid List<UserContactCreationDto> userContacts);

	@ApiOperation(value = "Registra contactos de un usuario en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Contactos obtenidos de manera correcta.", response = Mono.class),
			@ApiResponse(code = 400, message = "Los parámetros de entrada no son correctos.", response = Mono.class),
			@ApiResponse(code = 404, message = "No se han encontrado datos.", response = Mono.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = Mono.class) })
	public ResponseEntity<Mono<List<UserContactDto>>> getCommonContacts(
			@ApiParam(value = "Teléfono de usuario.", required = true) @RequestParam("userId1") @NotNull String userId1,
			@ApiParam(value = "Teléfono de usuario.", required = true) @RequestParam("userId2") @NotNull String userId2);

	@ApiOperation(value = "Registra contactos de un usuario en el sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Contactos obtenidos de manera correcta.", response = Mono.class),
			@ApiResponse(code = 400, message = "Los parámetros de entrada no son correctos.", response = Mono.class),
			@ApiResponse(code = 404, message = "No se han encontrado datos.", response = Mono.class),
			@ApiResponse(code = 500, message = "Error interno del servidor.", response = Mono.class) })
	public ResponseEntity<Mono<List<UserContactDto>>> getUserContacts(
			@ApiParam(value = "Teléfono de usuario.", required = true) @RequestParam("userPhone") @NotNull String userPhone);
}
