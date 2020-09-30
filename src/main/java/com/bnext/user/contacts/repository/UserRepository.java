package com.bnext.user.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnext.user.contacts.entity.User;

/**
 * User Repository.
 * @author abarreda
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Searchs user by phone key.
	 * @param phone phone key.
	 * @return user by phone key.
	 */
	User findFirstByPhone(final String phone);
}
