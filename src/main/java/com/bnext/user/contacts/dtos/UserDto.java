package com.bnext.user.contacts.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User exchange object.
 * 
 * @author abarreda
 *
 */
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	/**
	/**
	 * User name
	 */
	@ApiModelProperty(name = "name", value = "Nombre del usuario.", position = 0, required = true, example = "Alejandro")
	@NotNull
	@NotEmpty
	private String name;
	/**
	 * User last name.
	 */
	@ApiModelProperty(name = "lastName", value = "Apellido del usuario.", position = 1, required = true, example = "Barreda")
	@NotNull
	@NotEmpty
	private String lastName;
	/**
	 * User phone.
	 */
	@ApiModelProperty(name = "phone", value = "Teléfono del contacto.", position = 2, required = true, example = "675926967")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\+?[0-9]{3}-?[0-9]{6,12}$")
	private String phone;
}
