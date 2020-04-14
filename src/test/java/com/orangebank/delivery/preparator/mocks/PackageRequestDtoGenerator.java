package com.orangebank.delivery.preparator.mocks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;

/**
 * Generador de objeto Mock para la clase PackageRequestDto.
 * 
 * @author abarreda
 * @see PackageRequestDto
 *
 */
public class PackageRequestDtoGenerator {

	private final static String SENDER = "Alejandro Barreda";
	private final static String BAD_SENDER = "";
	private final static String SHIPPING_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
	private final static String BAD_SHIPPING_DATE_FORMAT = "08-04-2020";
	private final static String BAD_SHIPPING_DATE = "08/04/2020";

	public static PackageRequestDto generatePackageRequestDto() {
		return PackageRequestDto.builder().sender(SENDER).shippingDate(SHIPPING_DATE)
				.packages(Collections.singletonList(PackageDtoGenerator.generatePackageDto())).build();
	}
	
	public static PackageRequestDto generateBadPackageRequestDto() {
		return PackageRequestDto.builder().sender(BAD_SENDER).shippingDate(BAD_SHIPPING_DATE_FORMAT)
				.packages(Collections.singletonList(PackageDtoGenerator.generateBadPackageDto())).build();
	}
	
	public static PackageRequestDto generateBadPackageRequestDtoWithDateNotValid() {
		return PackageRequestDto.builder().sender(BAD_SENDER).shippingDate(BAD_SHIPPING_DATE)
				.packages(Collections.singletonList(PackageDtoGenerator.generateBadPackageDto())).build();
	}
	
	public static PackageRequestDto generateBadPackageRequestDtoWithCpNotValidInSystem() {
		return PackageRequestDto.builder().sender(BAD_SENDER).shippingDate(BAD_SHIPPING_DATE)
				.packages(Collections.singletonList(PackageDtoGenerator.generateBadPackageDtoWithCpNotValidInSystem())).build();
	}
}
