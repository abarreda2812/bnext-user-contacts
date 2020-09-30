package com.orangebank.delivery.preparator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.orangebank.delivery.preparator.exceptions.DeliveryPreparatorException;
import com.orangebank.delivery.preparator.mapper.impl.PackageRequestDtoJSONMapperImpl;
import com.orangebank.delivery.preparator.mocks.PackageRequestDtoGenerator;
import com.orangebank.delivery.preparator.mocks.TestExceptionDB;
import com.orangebank.delivery.preparator.service.impl.SenderDeliveryProcessor;

@ExtendWith(MockitoExtension.class)
public class SenderDeliverProcessorTest {

	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;
	private PackageRequestDtoJSONMapperImpl jsonMapper = new PackageRequestDtoJSONMapperImpl();
	private final String senderTopic = "package-request";

	@InjectMocks
	private SenderDeliveryProcessor senderDeliveryProcessor = new SenderDeliveryProcessor(kafkaTemplate, jsonMapper,
			senderTopic);

	@Test
	public void packageRequestProcess_SuccessFullTest() {
		senderDeliveryProcessor.sendDeliveryForProcessor(PackageRequestDtoGenerator.generatePackageRequestDto());
		Mockito.verify(kafkaTemplate).send(senderTopic,
				jsonMapper.fromDtoToJSON(PackageRequestDtoGenerator.generatePackageRequestDto()));
	}

	@Test
	public void packageRequestProcess_ErrorTest() {
		Mockito.doThrow(new TestExceptionDB("Error")).when(kafkaTemplate).send(Mockito.anyString(), Mockito.anyString());
		try {
			senderDeliveryProcessor.sendDeliveryForProcessor(PackageRequestDtoGenerator.generatePackageRequestDto());
		} catch (DeliveryPreparatorException e) {
			Assertions.assertNotNull(e.getMessage());
		}
		Mockito.verify(kafkaTemplate).send(senderTopic,
				jsonMapper.fromDtoToJSON(PackageRequestDtoGenerator.generatePackageRequestDto()));
		
	}
}
