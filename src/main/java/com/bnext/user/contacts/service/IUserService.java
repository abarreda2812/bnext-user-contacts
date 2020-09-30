package com.bnext.user.contacts.service;

import com.bnext.user.contacts.dtos.UserDto;

import reactor.core.publisher.Mono;
/**
 * Service for users operations.
 * @author abarreda
 *
 */
public interface IUserService {
	/**
	 * Creates an user.
	 * @param userDto user to create.
	 * @return user created.
	 */
	public Mono<UserDto> createUser(UserDto userDto);
	
}
