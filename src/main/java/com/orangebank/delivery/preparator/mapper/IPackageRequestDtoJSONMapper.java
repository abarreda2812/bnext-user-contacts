package com.orangebank.delivery.preparator.mapper;

import com.orangebank.delivery.preparator.dtos.PackageRequestDto;

public interface IPackageRequestDtoJSONMapper {
	public String fromDtoToJSON(PackageRequestDto dto);
}
