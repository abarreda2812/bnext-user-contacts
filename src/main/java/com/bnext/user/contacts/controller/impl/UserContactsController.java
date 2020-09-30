package com.bnext.user.contacts.controller.impl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bnext.user.contacts.controller.IUserContactsController;
import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.dtos.UserDto;
import com.bnext.user.contacts.service.IUserContactService;
import com.bnext.user.contacts.service.IUserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * API controller for create Users and Contacts implementation.
 * 
 * @author abarreda
 *
 */
@RestController
@Validated
@Slf4j
public class UserContactsController implements IUserContactsController {

	/**
	 * User service.
	 */
	private IUserService userService;
	/**
	 * User contact service.
	 */
	private IUserContactService userContactService;

	@Autowired
	public UserContactsController(IUserService userService, IUserContactService userContactService) {
		this.userService = userService;
		this.userContactService = userContactService;
	}

	@Override
	@PutMapping("/createUser")
	public ResponseEntity<Mono<UserDto>> createUser(@Valid UserDto userDto) {
		log.info("@createUser creando usuario.");
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
	}

	@Override
	@PutMapping("/createContacts")
	public ResponseEntity<Mono<List<UserContactDto>>> createContacts(@Valid @RequestBody List<UserContactCreationDto> userContacts) {
		log.info("@createContacts creando contactos de usuario.");
		return new ResponseEntity<>(userContactService.createContacts(userContacts), HttpStatus.OK);
	}

	@Override
	@PostMapping("/getCommonContacts")
	public ResponseEntity<Mono<List<UserContactDto>>> getCommonContacts(@NotNull String userId1, @NotNull String userId2) {
		log.info("@getCommonContacts obteniendo contactos comunes de usuarios.");
		return new ResponseEntity<>(userContactService.getCommonContacts(userId1, userId2), HttpStatus.OK);
	}

	@Override
	@PostMapping("/getUserContacts")
	public ResponseEntity<Mono<List<UserContactDto>>> getUserContacts(@NotNull String userPhone) {
		log.info("@getUserContacts obteniendo contactos de usuario.");
		return new ResponseEntity<>(userContactService.getUserContacts(userPhone), HttpStatus.OK);
	}

}
