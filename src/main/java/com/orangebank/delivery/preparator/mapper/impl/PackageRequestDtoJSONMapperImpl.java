package com.orangebank.delivery.preparator.mapper.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import com.orangebank.delivery.preparator.mapper.IPackageRequestDtoJSONMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PackageRequestDtoJSONMapperImpl implements IPackageRequestDtoJSONMapper {

	@Override
	public String fromDtoToJSON(PackageRequestDto dto) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = dto!=null ? mapper.writeValueAsString(dto) : null;
		} catch (IOException e) {
			log.error("Eror al convertir a JSON el objeto:"+dto, e);
		}
		return result;
	}

}
