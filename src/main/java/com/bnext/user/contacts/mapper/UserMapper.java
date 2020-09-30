package com.bnext.user.contacts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bnext.user.contacts.dtos.UserDto;
import com.bnext.user.contacts.entity.User;

/**
 * User mapper between entity and dto.
 * @author abarreda
 *
 */
@Mapper
public interface UserMapper {
	UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
	/**
	 * Convertes to Dto object.
	 * @param user entity object.
	 * @return	Dto object.
	 */
	UserDto toDto(final User user);
	/**
	 * Convertes to entity object.
	 * @param userDto Dto object.
	 * @return Entity object.
	 */
	User toEntity(final UserDto userDto);
}
