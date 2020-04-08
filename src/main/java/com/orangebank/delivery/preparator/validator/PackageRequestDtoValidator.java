package com.orangebank.delivery.preparator.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.orangebank.delivery.preparator.dtos.PackageDto;
import com.orangebank.delivery.preparator.dtos.PackageRequestDto;

/**
 * Validador de objeto de registro de un pedido.
 * 
 * @author abarreda
 *
 */
@Component
public class PackageRequestDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PackageRequestDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PackageRequestDto request = (PackageRequestDto) target;

		// Validar - remitente
		if (request.getSender() == null || request.getSender().isEmpty()) {
			errors.rejectValue("sender", "Sender no válido, vacío o null.");
		}

		// Validar - fecha de envío
		if (request.getShippingDate() == null || request.getSender().isEmpty()) {
			errors.rejectValue("shippingDate", "Fecha de envío no válida, vacía o null.");
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				format.parse(request.getShippingDate());
			} catch (ParseException e) {
				errors.rejectValue("shippingDate", "Formato de fecha de envío no válida.");
			}

		}
		// Validar - paquetes
		if (request.getPackages().isEmpty()) {
			errors.rejectValue("packages", "Listado de paquetes vacío.");
		} else {
			request.getPackages().forEach(p->this.validatePackage(errors, p));
		}
	}
	
	/**
	 * Valida un paquete incluido en el pedido.
	 * @param errors
	 * @param packageDto
	 */
	private void validatePackage(Errors errors, PackageDto packageDto) {
		// Validar - packageId
		if (packageDto.getPackageId() == null || packageDto.getPackageId().isEmpty()) {
			errors.rejectValue("packageId", "Identificador de paquete no válido, vacío o null.");
		} else if (packageDto.getPackageId().matches("\\d{8}-\\d{4}-\\d{4}-\\d{4}-\\d{12}")) {
			errors.rejectValue("packageId", "Identificador de paquete no válido, formato no válido.");
		}
		// Validar - address
		if (packageDto.getAddress() == null || packageDto.getAddress().isEmpty()) {
			errors.rejectValue("address", "Dirección de envío no válida, vacía o null.");
		}
		// Validar - city
		if (packageDto.getCity() == null || packageDto.getCity().isEmpty()) {
			errors.rejectValue("city", "Ciuadad no válida, vacía o null.");
		}
		// Validar - cp
		if (String.valueOf(packageDto.getCp()).matches("\\^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$/")) {
			errors.rejectValue("cp", "Formato de código postal no valido");
		}
		// Validar - weight
		if (packageDto.getWeight() > 0) {
			errors.rejectValue("weight", "Formato de peso de paquete no valido.");
		}
	}

}
