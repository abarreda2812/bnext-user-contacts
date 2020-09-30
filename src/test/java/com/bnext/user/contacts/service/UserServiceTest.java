package com.bnext.user.contacts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bnext.user.contacts.dtos.UserDto;
import com.bnext.user.contacts.entity.User;
import com.bnext.user.contacts.repository.UserRepository;
import com.bnext.user.contacts.service.impl.UserService;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	@Disabled
	@Test
	public void createUserTest() {
		final UserDto serviceResponse = mock(UserDto.class);
		final User repositoryResponse = mock(User.class);
		when(userRepository.save(any())).thenReturn(repositoryResponse);
		final UserDto userDto = UserDto.builder().build();
		final Mono<UserDto> processResponse = userService.createUser(userDto);
		assertNotNull(processResponse);
		processResponse.subscribe(result -> assertEquals(serviceResponse, result),
				error -> fail("This step should never be reached"));
		verify(userRepository, times(1)).save(any());
	}

}
