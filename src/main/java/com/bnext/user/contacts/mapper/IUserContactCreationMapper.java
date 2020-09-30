package com.bnext.user.contacts.mapper;

import java.util.List;

import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.entity.UserContact;

/**
 * User contact mapper between entity and dto.
 * @author abarreda
 *
 */
public interface IUserContactCreationMapper {
	/**
	 * Converts to entity object.
	 * @param userContactDto Dto object.
	 * @return Entity object.
	 */
	UserContact toEntity(final UserContactCreationDto userDto);
	/**
	 * Converts list to entity object.
	 * @param userContactDto Dto object.
	 * @return Entity object list.
	 */
	List<UserContact> toEntityList(final List<UserContactCreationDto> userDtos);
}
