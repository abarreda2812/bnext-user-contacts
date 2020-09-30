package com.bnext.user.contacts.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User contact exchange object.
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
@ApiModel(value = "UserContactCreationDto", description = "Objeto de creación de contacto de un usuario.")
public class UserContactCreationDto {
	/**
	 * User name
	 */
	@ApiModelProperty(name = "contactName", value = "Nombre del contacto.", position = 0, required = true, example = "Roberto Perez")
	@NotNull
	@NotEmpty
	private String contactName;
	/**
	 * User phone.
	 */
	@ApiModelProperty(name = "phone", value = "Teléfono del contacto.", position = 1, required = true, example = "675926969")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\+?[0-9]{3}-?[0-9]{6,12}$")
	private String phone;
	/**
	 * User foreign key.
	 */
	@ApiModelProperty(name = "userKey", value = "Usuario asociado al contacto.", position = 2, required = true, example = "675926967")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^\\+?[0-9]{3}-?[0-9]{6,12}$")
	private String userKey;
}
