package com.bnext.user.contacts.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bnext.user.contacts.dtos.UserContactDto;
import com.bnext.user.contacts.entity.UserContact;

/**
 * User contact mapper between entity and dto.
 * @author abarreda
 *
 */
@Mapper
public interface UserContactMapper {
	UserContactMapper USER_CONTACT_MAPPER = Mappers.getMapper(UserContactMapper.class);
	/**
	 * Converts to Dto object.
	 * @param user contact entity object.
	 * @return	Dto object.
	 */
	UserContactDto toDto(final UserContact user);
	/**
	 * Converts to entity object.
	 * @param userContactDto Dto object.
	 * @return Entity object.
	 */
	UserContact toEntity(final UserContactDto userDto);
	/**
	 * Converts list to Dto object.
	 * @param user contact list entity object.
	 * @return	Dto objects.
	 */
	List<UserContactDto> toDto(final List<UserContact> users);
	/**
	 * Converts to entity objects.
	 * @param userContactDto Dto objects.
	 * @return Entity objects.
	 */
	List<UserContact> toEntity(final List<UserContactDto> userDtos);
}
