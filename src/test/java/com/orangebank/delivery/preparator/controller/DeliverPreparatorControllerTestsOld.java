package com.orangebank.delivery.preparator.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.orangebank.delivery.preparator.controller.impl.DeliveryPreparatorController;
import com.orangebank.delivery.preparator.dtos.NotificationDto;
import com.orangebank.delivery.preparator.fakes.CpRepositoryFakeExistByIdFalse;
import com.orangebank.delivery.preparator.fakes.CpRepositoryFakeExistByIdTrue;
import com.orangebank.delivery.preparator.mocks.PackageRequestDtoGenerator;
import com.orangebank.delivery.preparator.service.ISenderDeliveryProcessor;
import com.orangebank.delivery.preparator.validator.PackageRequestDtoValidator;
//FIXME revisar con mocks
@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//FIXME Consultar MockMVC
class DeliverPreparatorControllerTestsOld {
	
	@Mock
	private ISenderDeliveryProcessor senderDeliveryProcessor;
	@InjectMocks
	private DeliveryPreparatorController deliveryPreparatorController;

	@Test
	void prepareDelivery_SuccessfullTest() {
		CpRepositoryFakeExistByIdTrue cpRepository = new CpRepositoryFakeExistByIdTrue();
		PackageRequestDtoValidator packageRequestDtoValidator = new PackageRequestDtoValidator(cpRepository);
		deliveryPreparatorController = new DeliveryPreparatorController(
				senderDeliveryProcessor, packageRequestDtoValidator);
		ResponseEntity<NotificationDto> actual = deliveryPreparatorController
				.prepareDelivery(PackageRequestDtoGenerator.generatePackageRequestDto());
		Assertions.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
	}

	@Test
	void prepareDelivery_BadRequestErrorTest() {
		CpRepositoryFakeExistByIdTrue cpRepository = new CpRepositoryFakeExistByIdTrue();
		PackageRequestDtoValidator packageRequestDtoValidator = new PackageRequestDtoValidator(cpRepository);
		deliveryPreparatorController = new DeliveryPreparatorController(
				senderDeliveryProcessor, packageRequestDtoValidator);
		ResponseEntity<NotificationDto> actual = deliveryPreparatorController
				.prepareDelivery(PackageRequestDtoGenerator.generateBadPackageRequestDto());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
	}

	@Test
	void prepareDelivery_BadRequestError_DateNotValidTest() {
		// Bad date, out of time
		CpRepositoryFakeExistByIdTrue cpRepository = new CpRepositoryFakeExistByIdTrue();
		PackageRequestDtoValidator packageRequestDtoValidator = new PackageRequestDtoValidator(cpRepository);
		deliveryPreparatorController = new DeliveryPreparatorController(
				senderDeliveryProcessor, packageRequestDtoValidator);
		ResponseEntity<NotificationDto> actual = deliveryPreparatorController
				.prepareDelivery(PackageRequestDtoGenerator.generateBadPackageRequestDtoWithDateNotValid());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
	}

	@Test
	void prepareDelivery_BadRequestErrorTest_CpNotValidInSystem() {
		// CP out of list
		CpRepositoryFakeExistByIdFalse cpRepository = new CpRepositoryFakeExistByIdFalse();
		PackageRequestDtoValidator packageRequestDtoValidator = new PackageRequestDtoValidator(cpRepository);
		deliveryPreparatorController = new DeliveryPreparatorController(
				senderDeliveryProcessor, packageRequestDtoValidator);
		ResponseEntity<NotificationDto> actual = deliveryPreparatorController
				.prepareDelivery(PackageRequestDtoGenerator.generateBadPackageRequestDtoWithCpNotValidInSystem());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
	}

}
