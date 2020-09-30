package com.bnext.user.contacts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnext.user.contacts.controller.impl.UserContactsController;
import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.dtos.UserDto;
import com.bnext.user.contacts.service.IUserContactService;
import com.bnext.user.contacts.service.IUserService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class UserContactsControllerTest {
	@Mock
	private IUserService userService;
	@Mock
	private IUserContactService userContactService;
	@InjectMocks
	private UserContactsController userContactsController;

	@Test
	public void createUserTest() {
		final UserDto serviceResponse = mock(UserDto.class);
		when(userService.createUser(any())).thenReturn(Mono.just(serviceResponse));
		final UserDto userDto = UserDto.builder().build();
		final ResponseEntity<Mono<UserDto>> processResponse = userContactsController.createUser(userDto);
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userService, times(1)).createUser(userDto);
	}

	@Test
	public void createContactsTest() {
		final List<UserContactDto> serviceResponse = Stream.of(mock(UserContactDto.class)).collect(Collectors.toList());
		when(userContactService.createContacts(any())).thenReturn(Mono.just(serviceResponse));
		final List<UserContactCreationDto> userContactDtoList = Stream.of(UserContactCreationDto.builder().build())
				.collect(Collectors.toList());
		final ResponseEntity<Mono<List<UserContactDto>>> processResponse = userContactsController
				.createContacts(userContactDtoList);
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userContactService, times(1)).createContacts(userContactDtoList);
	}

	@Test
	public void getCommonContactsTest() {
		final List<UserContactDto> serviceResponse = Stream.of(mock(UserContactDto.class)).collect(Collectors.toList());
		when(userContactService.getCommonContacts(anyString(), anyString())).thenReturn(Mono.just(serviceResponse));
		final ResponseEntity<Mono<List<UserContactDto>>> processResponse = userContactsController
				.getCommonContacts("666832312", "666832313");
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userContactService, times(1)).getCommonContacts("666832312", "666832313");
	}

	@Test
	public void getUserContactsTest() {
		final List<UserContactDto> serviceResponse = Stream.of(mock(UserContactDto.class)).collect(Collectors.toList());
		when(userContactService.getUserContacts(anyString())).thenReturn(Mono.just(serviceResponse));
		final ResponseEntity<Mono<List<UserContactDto>>> processResponse = userContactsController
				.getUserContacts("666832312");
		assertEquals(HttpStatus.OK, processResponse.getStatusCode());
		processResponse.getBody().subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userContactService, times(1)).getUserContacts("666832312");
	}
}
