package com.orangebank.delivery.preparator.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.orangebank.delivery.preparator.controller.impl.DeliveryPreparatorController;
import com.orangebank.delivery.preparator.mapper.impl.PackageRequestDtoJSONMapperImpl;
import com.orangebank.delivery.preparator.mocks.PackageRequestDtoGenerator;
import com.orangebank.delivery.preparator.service.ISenderDeliveryProcessor;
import com.orangebank.delivery.preparator.validator.PackageRequestDtoValidator;

@WebMvcTest(DeliveryPreparatorController.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
class DeliverPreparatorControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ISenderDeliveryProcessor senderDeliveryProcessor;
	@MockBean
	private PackageRequestDtoValidator validator;
	private PackageRequestDtoJSONMapperImpl jsonMapper = new PackageRequestDtoJSONMapperImpl();


	@Test
	void prepareDelivery_SuccessfullTest() throws Exception {
		 this.mockMvc.perform(post("/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonMapper.fromDtoToJSON(PackageRequestDtoGenerator.generatePackageRequestDto())))
	                .andExpect(status().isCreated());
	}

	@Test
	void prepareDelivery_BadRequestErrorTest() throws Exception {
		this.mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.fromDtoToJSON(PackageRequestDtoGenerator.generateBadPackageRequestDto())))
                .andExpect(status().isBadRequest());
	}

	@Test
	void prepareDelivery_BadRequestError_DateNotValidTest() throws Exception {
		// Bad date, out of time
		this.mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.fromDtoToJSON(PackageRequestDtoGenerator.generateBadPackageRequestDtoWithDateNotValid())))
                .andExpect(status().isBadRequest());
	}

	@Test
	void prepareDelivery_BadRequestErrorTest_CpNotValidInSystem() throws Exception {
		// CP out of list
		this.mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.fromDtoToJSON(PackageRequestDtoGenerator.generateBadPackageRequestDtoWithCpNotValidInSystem())))
                .andExpect(status().isBadRequest());
	}

}
