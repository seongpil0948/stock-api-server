package kr.co.dsi.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

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
		return new OpenAPI().info(new Info().title("Abacus WEB Sample").description("Abacus WEB Sample Project").version("v1.0")
				.license(new License().name("Apache 2.0").url("http://abacus.co.kr"))).servers(servers);
	}
}
