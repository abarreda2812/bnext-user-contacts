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
 * User contact entity.
 * 
 * @author abarreda
 *
 */
@Entity
@Table(name = "BNEXT_USER_CONTACT")
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContact {
	/**
	 * Private key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_CONTACT_GENERATOR")
	@SequenceGenerator(name = "USER_CONTACT_GENERATOR", sequenceName = "USER_CONTACT_ID_GENERATOR", schema = "public", initialValue = 1, allocationSize = 1)
	private Long id;
	/**
	 * User name
	 */
	private String contactName;
	/**
	 * User phone.
	 */
	@Column
	private String phone;
	/**
	 * User foreign key.
	 */
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(
			  name = "BNEXT_USER_USER_CONTACT", 
			  joinColumns = @JoinColumn(name = "user_contact_id"), 
			  inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
}
