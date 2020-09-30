package com.bnext.user.contacts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bnext.user.contacts.entity.UserContact;

/**
 * User Contact Repository.
 * 
 * @author abarreda
 *
 */
@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {
	
	/**
	 * Obtains all contacts by an user phone.
	 * @param userPhone phone for filter.
	 * @return all contacts by an user phone.
	 */
	@Query("SELECT DISTINCT contact FROM UserContact contact "
			+ "INNER JOIN FETCH contact.users as user "
			+ "WHERE user.phone=?1")
	List<UserContact> findAllUserContactsByUserPhone(final String userPhone);
	
	/**
	 * Obtains all contacts by user phones.
	 * @param userPhones phones for filter.
	 * @return all contacts by user phones.
	 */
	@Query("SELECT DISTINCT contact FROM UserContact contact "
			+ "INNER JOIN FETCH contact.users as user "
			+ "WHERE user.phone in ?1")
	List<UserContact> findAllUserContactsByUserPhones(final List<String> userPhones);
}
