package com.bnext.user.contacts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.entity.UserContact;
import com.bnext.user.contacts.exception.UserContactsException;
import com.bnext.user.contacts.mapper.IUserContactCreationMapper;
import com.bnext.user.contacts.repository.UserContactRepository;
import com.bnext.user.contacts.service.impl.UserContactService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class UserContactServiceTest {
	@Mock
	private UserContactRepository userContactRepository;
	@Mock
	private IUserContactCreationMapper userContactCreationMapper;
	@InjectMocks
	private UserContactService userContactService;

	@Test
	public void createContactsTest() {
		final List<UserContactDto> serviceResponse = Stream.of(UserContactDto.builder().build())
				.collect(Collectors.toList());
		final List<UserContact> repositoryResponse = Stream.of(UserContact.builder().build())
				.collect(Collectors.toList());
		when(userContactRepository.saveAll(any())).thenReturn(repositoryResponse);
		when(userContactCreationMapper.toEntityList(any())).thenReturn(repositoryResponse);
		final List<UserContactCreationDto> userContactDtoList = Stream.of(UserContactCreationDto.builder().build())
				.collect(Collectors.toList());
		final Mono<List<UserContactDto>> processResponse = userContactService.createContacts(userContactDtoList);
		assertNotNull(processResponse);
		processResponse.subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userContactRepository, times(1)).saveAll(any());
	}

	@Test
	public void createContactsFailTest() {
		final Mono<List<UserContactDto>> processResponse = userContactService.createContacts(null);
		processResponse.subscribe(result -> fail("This step should never be reached"), error -> {
			assertTrue(error instanceof UserContactsException);
			ResponseStatusException ex = (ResponseStatusException) error;
			assertEquals(ex.getStatus(), HttpStatus.BAD_REQUEST);
		});
	}

	@Test
	public void getCommonContactsTest() {
		final List<UserContactDto> serviceResponse = Stream.of(UserContactDto.builder().build())
				.collect(Collectors.toList());
		final List<UserContact> repositoryResponse = Stream.of(UserContact.builder().build())
				.collect(Collectors.toList());
		when(userContactRepository.findAllUserContactsByUserPhones(any())).thenReturn(repositoryResponse);
		final Mono<List<UserContactDto>> processResponse = userContactService.getCommonContacts("666832312",
				"666832313");
		assertNotNull(processResponse);
		processResponse.subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userContactRepository, times(1)).findAllUserContactsByUserPhones(any());
	}

	@Test
	public void getCommonContactsBadRequestFailTest() {
		Mono<List<UserContactDto>> processResponse = userContactService.getCommonContacts(null, "666832313");
		processResponse.subscribe(result -> fail("This step should never be reached"), error -> {
			assertTrue(error instanceof UserContactsException);
			ResponseStatusException ex = (ResponseStatusException) error;
			assertEquals(ex.getStatus(), HttpStatus.BAD_REQUEST);
		});
		processResponse = userContactService.getCommonContacts("666832313", null);
		processResponse.subscribe(result -> fail("This step should never be reached"), error -> {
			assertTrue(error instanceof UserContactsException);
			ResponseStatusException ex = (ResponseStatusException) error;
			assertEquals(ex.getStatus(), HttpStatus.BAD_REQUEST);
		});
	}
	
	@Test
	public void getCommonContactsNotFoundFailTest() {
		final List<UserContact> repositoryResponse = new ArrayList<>();
		when(userContactRepository.findAllUserContactsByUserPhones(any())).thenReturn(repositoryResponse);
		final Mono<List<UserContactDto>> processResponse = userContactService.getCommonContacts("666832312",
				"666832313");
		assertNotNull(processResponse);
		processResponse.subscribe(result -> fail("This step should never be reached"), error -> {
			assertTrue(error instanceof UserContactsException);
			ResponseStatusException ex = (ResponseStatusException) error;
			assertEquals(ex.getStatus(), HttpStatus.NOT_FOUND);
		});
		verify(userContactRepository, times(1)).findAllUserContactsByUserPhones(any());
	}

	@Test
	public void getUserContactsTest() {
		final List<UserContactDto> serviceResponse = Stream.of(UserContactDto.builder().build())
				.collect(Collectors.toList());
		final List<UserContact> repositoryResponse = Stream.of(UserContact.builder().build())
				.collect(Collectors.toList());
		when(userContactRepository.findAllUserContactsByUserPhone(anyString())).thenReturn(repositoryResponse);
		final Mono<List<UserContactDto>> processResponse = userContactService.getUserContacts("666832312");
		assertNotNull(processResponse);
		processResponse.subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userContactRepository, times(1)).findAllUserContactsByUserPhone(anyString());
	}

	@Test
	public void getUserContactsBadRequestFailTest() {
		Mono<List<UserContactDto>> processResponse = userContactService.getUserContacts(null);
		processResponse.subscribe(result -> fail("This step should never be reached"), error -> {
			assertTrue(error instanceof UserContactsException);
			ResponseStatusException ex = (ResponseStatusException) error;
			assertEquals(ex.getStatus(), HttpStatus.BAD_REQUEST);
		});
	}
	
	@Test
	public void getUserContactsNotFoundFailTest() {
		final List<UserContact> repositoryResponse = new ArrayList<>();
		when(userContactRepository.findAllUserContactsByUserPhone(anyString())).thenReturn(repositoryResponse);
		final Mono<List<UserContactDto>> processResponse = userContactService.getUserContacts("666832312");
		assertNotNull(processResponse);
		processResponse.subscribe(result -> fail("This step should never be reached"), error -> {
			assertTrue(error instanceof UserContactsException);
			ResponseStatusException ex = (ResponseStatusException) error;
			assertEquals(ex.getStatus(), HttpStatus.NOT_FOUND);
		});
		verify(userContactRepository, times(1)).findAllUserContactsByUserPhone(anyString());
	}
}
