package com.orangebank.delivery.preparator.dtos;

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
 * Objeto que encapsula un bulto de un pedido.
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
@ApiModel(value = "PackageDto", description = "Objeto que encapsula un bulto de un pedido.")
public class PackageDto {
	@ApiModelProperty(name = "packageId", value = "Identificador del bulto. Debe seguir un patrón XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX", position = 0, required = true, example = "11111111-1111-1111-1111-11111111111")
	@JsonProperty("packageId")
	private String packageId;
	@ApiModelProperty(name = "address", value = "Dirección de envío del bulto.", position = 1, required = true, example = "C/Falsa, 123")
	@JsonProperty("address")
	private String address;
	@ApiModelProperty(name = "cp", value = "Código postal del envío del bulto.", position = 2, required = true, example = "28801")
	@JsonProperty("cp")
	private int cp;
	@ApiModelProperty(name = "city", value = "Ciudad de envío del pedido.", position = 3, required = true, example = "Madrid")
	@JsonProperty("city")
	private String city;
	@ApiModelProperty(name = "weight", value = "Peso del bulto del pedido.", position = 4, required = true, example = "3.1")
	@JsonProperty("weight")
	private double weight;
}
