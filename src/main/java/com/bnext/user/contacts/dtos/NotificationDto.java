package com.orangebank.delivery.preparator.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Encapsula una respuesta HTTP.
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
@EqualsAndHashCode
@ApiModel(value = "NotificationDto", description = "Encapsula una respuesta HTTP.")
public class NotificationDto {
	@ApiModelProperty(name = "status", value = "Código de estado HTTP.", position = 0, example = "400")
	@JsonProperty("status")
	private String status;
	@ApiModelProperty(name = "message", value = "Mensaje de respuesta.", position = 1, example = "Se ha producido un error de validación en servidor.")
	@JsonProperty("message")
	private String message;
	@ApiModelProperty(name = "errorCode", value = "Código de error.", position = 2, example = "El identificador del paquete no sigue un formato válido.")
	@JsonProperty("errorCode")
	private String errorCode;
}
