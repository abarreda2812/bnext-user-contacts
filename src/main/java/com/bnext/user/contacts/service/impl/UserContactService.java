package com.bnext.user.contacts.service.impl;

import static com.bnext.user.contacts.mapper.UserContactMapper.USER_CONTACT_MAPPER;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.entity.UserContact;
import com.bnext.user.contacts.exception.UserContactsException;
import com.bnext.user.contacts.mapper.IUserContactCreationMapper;
import com.bnext.user.contacts.repository.UserContactRepository;
import com.bnext.user.contacts.service.IUserContactService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * User contact service implementation.
 * 
 * @author abarreda
 *
 */
@Service
@Slf4j
@Transactional
public class UserContactService implements IUserContactService {

	/**
	 * User contact repository.
	 */
	private UserContactRepository userContactRepository;

	/**
	 * Mapper of creation object.
	 */
	private IUserContactCreationMapper userContactCreationMapper;

	@Autowired
	public UserContactService(final UserContactRepository userContactRepository,
			final IUserContactCreationMapper userContactCreationMapper) {
		this.userContactRepository = userContactRepository;
		this.userContactCreationMapper = userContactCreationMapper;
	}

	@Override
	public Mono<List<UserContactDto>> createContacts(List<UserContactCreationDto> userContacts) {
		log.info("@createContacts Creando contactos.");
		return Mono.justOrEmpty(userContacts)
				.switchIfEmpty(Mono.error(new UserContactsException(HttpStatus.BAD_REQUEST, "Contactos null")))
				.map(this.userContactCreationMapper::toEntityList).map(userContactRepository::saveAll)
				.flatMap(contacts -> Mono.just(USER_CONTACT_MAPPER.toDto(contacts)))
				.doOnSuccess(user -> log.info("Contactos guardados"))
				.doOnError(error -> log.error("Error guardando contactos", error));
	}

	@Override
	public Mono<List<UserContactDto>> getCommonContacts(String userId1, String userId2) {
		log.info("@getCommonContacts Buscando contactos.");
		return Mono.justOrEmpty(userId1)
				.switchIfEmpty(
						Mono.error(new UserContactsException(HttpStatus.BAD_REQUEST, "Filtro de teléfono1 null")))
				.flatMap(valueUserId1 -> {
					if (Objects.isNull(userId2) || userId2.isEmpty()) {
						return Mono
								.error(new UserContactsException(HttpStatus.BAD_REQUEST, "Filtro de teléfono2 null"));
					}
					List<UserContact> contacts = userContactRepository
							.findAllUserContactsByUserPhones(Stream.of(userId1, userId2).collect(Collectors.toList()));
					return checkSearchResult(contacts);
				}).doOnSuccess(user -> log.info("Contactos encontrados"))
				.doOnError(error -> log.error("Error buscando contactos", error));
	}

	@Override
	public Mono<List<UserContactDto>> getUserContacts(String userPhone) {
		log.info("@getUserContacts Buscando contactos.");
		return Mono.justOrEmpty(userPhone)
				.switchIfEmpty(Mono.error(new UserContactsException(HttpStatus.BAD_REQUEST, "Filtro de teléfono null")))
				.flatMap(valueUserPhone -> {
					List<UserContact> contacts = userContactRepository.findAllUserContactsByUserPhone(valueUserPhone);
					return checkSearchResult(contacts);
				}).doOnSuccess(user -> log.info("Contactos encontrados"))
				.doOnError(error -> log.error("Error buscando contactos", error));
	}

	/**
	 * Checks the search result for return exception Not Found if result is empty.
	 * 
	 * @param contacts search result.
	 * @return search result or exception NOT_FOUND.
	 */
	private Mono<List<UserContactDto>> checkSearchResult(List<UserContact> contacts) {
		if (contacts.isEmpty()) {
			return Mono.error(new UserContactsException(HttpStatus.NOT_FOUND, "No se encontraron resultados"));
		} else {
			return Mono.just(USER_CONTACT_MAPPER.toDto(contacts));
		}
	}

}
