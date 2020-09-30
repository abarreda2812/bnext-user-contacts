package com.bnext.user.contacts.service;

import java.util.List;

import com.bnext.user.contacts.dtos.UserContactCreationDto;
import com.bnext.user.contacts.dtos.UserContactDto;

import reactor.core.publisher.Mono;
/**
 * Service for user contacts operations.
 * @author abarreda
 *
 */
public interface IUserContactService {
	/**
	 * Creates user contacts.
	 * @param userContacts user contacts to create.
	 * @return user contacts created.
	 */
	public Mono<List<UserContactDto>> createContacts(List<UserContactCreationDto> userContacts);
	
	/**
	 * Obtains common contacts between two users.
	 * @param userId1 phone of user 1.
	 * @param userId2 phone of user 2.
	 * @return common contacts.
	 */
	public Mono<List<UserContactDto>> getCommonContacts(String userId1, String userId2);
	
	/**
	 * Obtains user contacts by user phone.
	 * @param userPhone phone of user.
	 * @return user contacts by user phone.
	 */
	public Mono<List<UserContactDto>> getUserContacts(String userPhone);
}
