package com.orangebank.delivery.preparator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto de registro de un pedido.
 * 
 * @author abarreda
 *
 */
@Entity
@Table(name = "POSTAL_CODES")
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CpData {
	@Id
	@Column(name="CP", length = 5)
	private String cp;
}
