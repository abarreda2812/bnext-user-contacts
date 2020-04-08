package com.orangebank.delivery.preparator.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto de registro de un pedido.
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
@ToString
@ApiModel(value = "PackageRequestDto", description = "Objeto de registro de un pedido.")
public class PackageRequestDto {
	@ApiModelProperty(name = "sender", value = "Remitente del pedido.", position = 0, required = true, example = "Alejandro Barreda")
	@JsonProperty("sender")
	private String sender;
	@ApiModelProperty(name = "shippingDate", value = "Código postal del envío del bulto.", position = 1, required = true, example = "08/05/2020")
	@JsonProperty("shippingDate")
	private String shippingDate;
	@ApiModelProperty(name = "packages", value = "Bultos del envio.", position = 2, required = true, example = "[{address: \"C/Falsa, 123\", cp: 28801, city: \"Madrid\", weight: 3.1},{address: \"C/Falsa, 123\", cp: 28801, city: \"Madrid\", weight: 5.7}]")
	@JsonProperty("packages")
	private List<PackageDto> packages;
}
