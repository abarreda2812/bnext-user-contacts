package com.orangebank.delivery.preparator.mocks;

import com.orangebank.delivery.preparator.dtos.PackageDto;
/**
 * Generador de objeto Mock para la clase PackageDto.
 * @author abarreda
 * @see PackageDto
 *
 */
public class PackageDtoGenerator {
	
	private final static String PACKAGE_ID = "11111111-1111-1111-1111-111111111111";
	private final static String ADDRESS = "C/Falsa, 123";
	private final static String CP = "28801";
	private final static String CITY = "Madrid";
	private final static double WEIGHT = 5.2;
	private final static String BAD_PACKAGE_ID = "11111111-1111-1111-1111-11111111";
	private final static String BAD_ADDRESS = "";
	private final static String BAD_CP = "2801";
	private final static String BAD_CITY = "";
	private final static double BAD_WEIGHT = 0;
	private final static String BAD_NOT_VALID_IN_SYSTEM_CP = "28808";
	
	
	public static PackageDto generatePackageDto() {
		return PackageDto.builder().packageId(PACKAGE_ID).address(ADDRESS).cp(CP).city(CITY).weight(WEIGHT).build();
	}
	
	public static PackageDto generateBadPackageDto() {
		return PackageDto.builder().packageId(BAD_PACKAGE_ID).address(BAD_ADDRESS).cp(BAD_CP).city(BAD_CITY).weight(BAD_WEIGHT).build();
	}
	
	public static PackageDto generateBadPackageDtoWithCpNotValidInSystem() {
		return PackageDto.builder().packageId(BAD_PACKAGE_ID).address(BAD_ADDRESS).cp(BAD_NOT_VALID_IN_SYSTEM_CP).city(BAD_CITY).weight(BAD_WEIGHT).build();
	}
}	
