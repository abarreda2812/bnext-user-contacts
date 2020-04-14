package com.orangebank.delivery.preparator.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.orangebank.delivery.preparator.dtos.PackageDto;
import com.orangebank.delivery.preparator.dtos.PackageRequestDto;
import com.orangebank.delivery.preparator.repository.CpRepository;

/**
 * Validador de objeto de registro de un pedido.
 * 
 * @author abarreda
 *
 */
@Component
public class PackageRequestDtoValidator implements Validator {
	
	/**
	 * Repositorio del sistema de códigos postales.
	 */
	private CpRepository cpRepository;

	@Autowired
	public PackageRequestDtoValidator(CpRepository cpRepository) {
		this.cpRepository = cpRepository;
	}

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
		if (request.getShippingDate() == null || request.getShippingDate().isEmpty()) {
			errors.rejectValue("shippingDate", "Fecha de envío no válida, vacía o null.");
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				// Validar formato de fecha correcto
				Date shippingDate = format.parse(request.getShippingDate());
				// Validar fecha es mayor o igual que hoy
				if (shippingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
						.isBefore(LocalDate.now().atTime(0, 0))) {
					errors.rejectValue("shippingDate",
							"La fecha de envío no es válida, debe ser mayor o igual al día actual.");
				}
			} catch (ParseException e) {
				errors.rejectValue("shippingDate", "Formato de fecha de envío no válida.");
			}
		}
		// Validar - paquetes
		if (request.getPackages().isEmpty()) {
			errors.rejectValue("packages", "Listado de paquetes vacío.");
		} else {
			for (int i = 0; i < request.getPackages().size(); i++) {
				this.validatePackage(errors, request.getPackages().get(i), i);
			}
		}
	}

	/**
	 * Valida un paquete incluido en el pedido.
	 * 
	 * @param errors
	 * @param packageDto
	 * @param index
	 */
	private void validatePackage(Errors errors, PackageDto packageDto, int index) {
		// Validar - packageId
		if (packageDto.getPackageId() == null || packageDto.getPackageId().isEmpty()) {
			errors.rejectValue("packages[" + index + "].packageId",
					"Identificador de paquete no válido, vacío o null.");
		} else if (!packageDto.getPackageId().matches("\\d{8}-\\d{4}-\\d{4}-\\d{4}-\\d{12}")) {
			errors.rejectValue("packages[" + index + "].packageId",
					"Identificador de paquete no válido, formato no válido.");
		}
		// Validar - address
		if (packageDto.getAddress() == null || packageDto.getAddress().isEmpty()) {
			errors.rejectValue("packages[" + index + "].address", "Dirección de envío no válida, vacía o null.");
		}
		// Validar - city
		if (packageDto.getCity() == null || packageDto.getCity().isEmpty()) {
			errors.rejectValue("packages[" + index + "].city", "Ciuadad no válida, vacía o null.");
		}
		this.validateCp(errors, packageDto, index);
		// Validar - weight
		if (packageDto.getWeight() <= 0) {
			errors.rejectValue("packages[" + index + "].weight", "Formato de peso de paquete no valido.");
		}
	}

	/**
	 * Valida un código postal
	 * 
	 * @param errors
	 * @param packageDto
	 * @param index
	 */
	private void validateCp(Errors errors, PackageDto packageDto, int index) {
		// Validación de si tiene
		if (packageDto.getCp() == null || packageDto.getCp().isEmpty()
				|| !String.valueOf(packageDto.getCp()).matches("^(5[0-2]|[0-4][0-9])[0-9]{3}$")) {
			errors.rejectValue("packages[" + index + "].cp", "Formato de código postal no valido");
		}
		//Comprobamos si es un código postal válido para el sistema consultando en BD.
		if (!cpRepository.existsById(packageDto.getCp())) {
			errors.rejectValue("packages[" + index + "].cp",
					"El código postal:" + packageDto.getCp() + " no se encuentra entre los códigos postales válidos.");
		}
	}


}
