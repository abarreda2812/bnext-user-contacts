package com.bnext.user.contacts.mapper.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.entity.User;
import com.bnext.user.contacts.entity.UserContact;
import com.bnext.user.contacts.exception.UserContactsException;
import com.bnext.user.contacts.mapper.IUserContactCreationMapper;
import com.bnext.user.contacts.repository.UserRepository;

/**
 * User contact mapper between entity and dto.
 * 
 * @author abarreda
 *
 */
@Component
public class UserContactCreationMapper implements IUserContactCreationMapper {
	/**
	 * User repository.
	 */
	private UserRepository userRepository;

	@Autowired
	public UserContactCreationMapper(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Converts to entity object.
	 * 
	 * @param userContactDto Dto object.
	 * @return Entity object.
	 */
	@Override
	@Transactional
	public UserContact toEntity(final UserContactCreationDto userDto) throws UserContactsException {
		if (userDto == null) {
			return null;
		}

		UserContact userContact = new UserContact();

		userContact.setContactName(userDto.getContactName());
		userContact.setPhone(userDto.getPhone());
		if (Objects.nonNull(userDto.getUserKey()) && !userDto.getUserKey().isEmpty()) {
			User user = userRepository.findFirstByPhone(userDto.getUserKey());
			if (Objects.nonNull(user)) {
				user.getUserContacts().add(userContact);
				userContact.setUsers(Stream.of(user).collect(Collectors.toList()));
			} else {
				throw new UserContactsException(HttpStatus.NOT_FOUND, "No se ha encontrado usuario en el sistema.");
			}
		} else {
			throw new UserContactsException(HttpStatus.BAD_REQUEST, "El dato del usuario a guardar contacto es null.");
		}

		return userContact;
	}

	@Override
	public List<UserContact> toEntityList(List<UserContactCreationDto> userDtos) {
		if (userDtos == null || userDtos.isEmpty()) {
			return null;
		}
		return userDtos.stream().map(this::toEntity).collect(Collectors.toList());
	}
}
