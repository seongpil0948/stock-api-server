package com.stock.sp.apiserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProgramName : SwaggerConfig
 * @Author : LaVega
 * @Version : 1.0
 * @Description :
 */
@Configuration
public class SwaggerConfig {

	@Bean
	@ConfigurationProperties("springdoc.servers")
	public List<Server> getSpringDocServers() {
		return new ArrayList<>();
	}

	@Bean
	public OpenAPI api() {
		List<Server> servers = getSpringDocServers();
		return new OpenAPI().info(new Info().title("IDP Service")
				.description("LGUplus IDP Project")
				.version("v1.0")
				.license(new License().name("Apache 2.0").url("http://abacus.co.kr")))
				.servers(servers);
	}

	@Bean
	public GroupedOpenApi businessGroupOpenApi() {
		String packagesToScan[] = {"kr.co.idp.common", "kr.co.idp.business", "kr.co.idp.system"};
		return GroupedOpenApi.builder()
				.group("Business")
				.packagesToScan(packagesToScan)
				.build();
	}

	@Bean
	public GroupedOpenApi externalGroupOpenApi() {
		String packagesToScan[] = {"kr.co.idp.external"};
		return GroupedOpenApi.builder()
				.group("External")
				.packagesToScan(packagesToScan)
				.build();
	}
}
