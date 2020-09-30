package com.orangebank.delivery.preparator.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import com.orangebank.delivery.preparator.exceptions.DeliveryPreparatorException;
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
	private String sendTopic;

	@Autowired
	public SenderDeliveryProcessor(KafkaTemplate<String, String> kafkaTemplate, IPackageRequestDtoJSONMapper jsonMapper,
			@Value("${spring.kafka.producer.sendTopic}") String sendTopic) {
		this.kafkaTemplate = kafkaTemplate;
		this.jsonMapper = jsonMapper;
		this.sendTopic = sendTopic;
	}

	@Override
	public void sendDeliveryForProcessor(PackageRequestDto packageRequestDto) {
		log.info("@sendDeliveryForProcessor Enviando paquetes:" + packageRequestDto);
		try {
			this.kafkaTemplate.send(this.sendTopic, this.jsonMapper.fromDtoToJSON(packageRequestDto));
		} catch (final Throwable t) {
			throw new DeliveryPreparatorException(t.getMessage(), "Error al enviar el mensaje mediante kafka");
		}
	}

}
