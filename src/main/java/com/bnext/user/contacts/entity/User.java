package com.bnext.user.contacts.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity.
 * 
 * @author abarreda
 *
 */
@Entity
@Table(name = "BNEXT_USER")
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	/**
	 * Private key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GENERATOR")
	@SequenceGenerator(name = "USER_GENERATOR", sequenceName = "USER_ID_GENERATOR", schema = "public", initialValue = 1, allocationSize = 1)
	private Long id;
	/**
	 * User name
	 */
	private String name;
	/**
	 * User last name.
	 */
	private String lastName;
	/**
	 * User phone.
	 */
	@Column(unique = true)
	private String phone;
	/**
	 * User contacts.
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			  name = "BNEXT_USER_USER_CONTACT", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_contact_id"))
	private List<UserContact> userContacts;
}
