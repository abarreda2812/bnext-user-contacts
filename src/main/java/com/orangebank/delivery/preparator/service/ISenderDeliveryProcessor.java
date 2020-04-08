package com.orangebank.delivery.preparator.service;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
/**
 * Interfaz de envío de pedidos al procesador de pedidos.
 * @author abarreda
 *
 */
public interface ISenderDeliveryProcessor {

	public void sendDeliveryForProcessor(PackageRequestDto packageRequestDto);
}
