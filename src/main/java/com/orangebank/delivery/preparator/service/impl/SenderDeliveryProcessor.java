package com.orangebank.delivery.preparator.service.impl;

import org.springframework.stereotype.Service;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import com.orangebank.delivery.preparator.service.ISenderDeliveryProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementación de la interfaz de envío de pedidos al procesador de pedidos.
 * 
 * @author abarreda
 *
 */
@Service
@Slf4j
public class SenderDeliveryProcessor implements ISenderDeliveryProcessor {

	@Override
	public void sendDeliveryForProcessor(PackageRequestDto packageRequestDto) {
		log.info("@sendDeliveryForProcessor Enviando paquetes:" + packageRequestDto);

	}

}
