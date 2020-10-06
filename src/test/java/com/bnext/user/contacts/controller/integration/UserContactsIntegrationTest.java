package com.bnext.user.contacts.controller.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnext.user.contacts.controller.IUserContactsController;
import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.dtos.UserDto;

import reactor.core.publisher.Mono;

public class UserContactsIntegrationTest extends BaseIntegrationTest {
	@Autowired
	private IUserContactsController controller;

	@Test
	public void createUserProcess() {
		UserDto request = UserDto.builder().lastName("Barreda3").name("Alejandro").phone("675926962").build();
		ResponseEntity<Mono<UserDto>> processResponse = controller.createUser(request);
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals(request.getPhone(), result.getPhone()),
				error -> fail("This step should never be reached"));
	}

	@Test
	public void createContactsProcess() {
		UserContactCreationDto request = UserContactCreationDto.builder().contactName("Roberto Perez2")
				.phone("675926961").userKey("675926967").build();
		final ResponseEntity<Mono<List<UserContactDto>>> processResponse = controller
				.createContacts(Stream.of(request).collect(Collectors.toList()));
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals(request.getPhone(), result.get(0).getPhone()),
				error -> fail("This step should never be reached"));
	}

	@Test
	public void getCommonContactsProcess() {
		final ResponseEntity<Mono<List<UserContactDto>>> processResponse = controller.getCommonContacts("675926967",
				"675926969");
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals("675926968", result.get(0).getPhone()),
				error -> fail("This step should never be reached"));
	}

	@Test
	public void getUserContactsProcess() {
		final ResponseEntity<Mono<List<UserContactDto>>> processResponse = controller.getUserContacts("675926967");
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals("675926968", result.get(0).getPhone()),
				error -> fail("This step should never be reached"));
	}
}
