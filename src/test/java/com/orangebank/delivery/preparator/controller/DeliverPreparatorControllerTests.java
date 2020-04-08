package com.orangebank.delivery.preparator.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.orangebank.delivery.preparator.controller.impl.DeliveryPreparatorController;
import com.orangebank.delivery.preparator.exceptions.DeliveryPreparatorException;
import com.orangebank.delivery.preparator.mocks.PackageRequestDtoGenerator;
import com.orangebank.delivery.preparator.service.ISenderDeliveryProcessor;

@ExtendWith(MockitoExtension.class)
class DeliverPreparatorControllerTests {
	@InjectMocks
	private DeliveryPreparatorController deliveryPreparatorController;
	@Mock
	private ISenderDeliveryProcessor senderDeliveryProcessor;

	@Test
	void prepareDelivery_SuccessfullTest() {
		ResponseEntity<Void> actual = deliveryPreparatorController.prepareDelivery(PackageRequestDtoGenerator.generatePackageRequestDto());
		Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
	
	@Test
	void prepareDelivery_BadRequestErrorTest() {
		ResponseEntity<Void> actual = deliveryPreparatorController.prepareDelivery(PackageRequestDtoGenerator.generateBadPackageRequestDto());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
	}
	
	//TODO meter en el paso de establecer mensajería kafka
	@Disabled
	@Test
	void prepareDelivery_InternalErrorTest() {
		Mockito.doThrow(DeliveryPreparatorException.class).when(senderDeliveryProcessor).sendDeliveryForProcessor(Mockito.any());
		ResponseEntity<Void> actual = deliveryPreparatorController.prepareDelivery(PackageRequestDtoGenerator.generatePackageRequestDto());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
	}

}
