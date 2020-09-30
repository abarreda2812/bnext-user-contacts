package com.bnext.user.contacts.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bnext.user.contacts.dtos.UserDto;
import com.bnext.user.contacts.exception.UserContactsException;
import com.bnext.user.contacts.repository.UserRepository;
import com.bnext.user.contacts.service.IUserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static com.bnext.user.contacts.mapper.UserMapper.USER_MAPPER;

/**
 * User service implementation.
 * 
 * @author abarreda
 *
 */
@Service
@Slf4j
@Transactional
public class UserService implements IUserService {

	/**
	 * User repository.
	 */
	private UserRepository userRepository;

	@Autowired
	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Mono<UserDto> createUser(UserDto userDto) {
		log.info("@createUser Creando usuario.");
		return Mono.justOrEmpty(userDto)
				.switchIfEmpty(Mono.error(new UserContactsException(HttpStatus.BAD_REQUEST, "Usuario null")))
				.map(USER_MAPPER::toEntity).map(userRepository::save)
				.flatMap(user -> Mono.just(USER_MAPPER.toDto(user))).doOnSuccess(user -> log.info("Usuario guardado"))
				.doOnError(error -> log.error("Error guardando usuario", error));
	}

}
