package com.orangebank.delivery.preparator.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import com.orangebank.delivery.preparator.mapper.IPackageRequestDtoJSONMapper;
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
@Transactional
public class SenderDeliveryProcessor implements ISenderDeliveryProcessor {
	
	private KafkaTemplate<String, String> kafkaTemplate;
	private IPackageRequestDtoJSONMapper jsonMapper;
	
	/**
	 * Tópico de kafka donde vamos a enviar los paquetes.
	 */
	private static final String TOPIC = "package-request";
	
	@Autowired
	public SenderDeliveryProcessor(KafkaTemplate<String, String> kafkaTemplate,
			IPackageRequestDtoJSONMapper jsonMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.jsonMapper = jsonMapper;
	}

	@Override
	public void sendDeliveryForProcessor(PackageRequestDto packageRequestDto) {
		log.info("@sendDeliveryForProcessor Enviando paquetes:" + packageRequestDto);
		this.kafkaTemplate.send(TOPIC, jsonMapper.fromDtoToJSON(packageRequestDto));
	}
}
