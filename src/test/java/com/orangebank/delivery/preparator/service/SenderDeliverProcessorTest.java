package com.orangebank.delivery.preparator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.orangebank.delivery.preparator.mapper.impl.PackageRequestDtoJSONMapperImpl;
import com.orangebank.delivery.preparator.mocks.PackageRequestDtoGenerator;
import com.orangebank.delivery.preparator.service.impl.SenderDeliveryProcessor;

@ExtendWith(MockitoExtension.class)
public class SenderDeliverProcessorTest {

	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;
	@Spy
	private PackageRequestDtoJSONMapperImpl jsonMapper = new PackageRequestDtoJSONMapperImpl();

	@InjectMocks
	private SenderDeliveryProcessor senderDeliveryProcessor = new SenderDeliveryProcessor(kafkaTemplate, jsonMapper);

	@Test
	public void packageRequestProcess_SuccessFullTest() {
		senderDeliveryProcessor.sendDeliveryForProcessor(PackageRequestDtoGenerator.generatePackageRequestDto());
	}
}
