package com.bnext.user.contacts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Api config.
 * @author abarreda
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${user.contacts.version}")
	private String version;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bnext.user.contacts"))
				.paths(PathSelectors.any()).build()
				.apiInfo(getApiInfo())
				.tags(new Tag("UserContactsController", "Controlador con operaciones para alta de usuarios y contactos de usuarios."));
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("BNext Contactos de usuarios.").description(
				"API REST con las operaciones necesarias para dar de alta usuarios y números de teléfono.")
				.version(version).build();
	}

}
