package kr.co.dsi.spring.config;

import kr.co.dsi.common.exception.ExceptionInfoConfig;
import kr.co.dsi.common.utils.ApplicationContextManager;
import kr.co.dsi.spring.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = "kr.co.idp")
public class DsiComponentConfig {
	@Bean
	public ApplicationContextManager getApplicationManager() {
		return new ApplicationContextManager();
	}

	@Bean
	public ControllerInterceptor controllerInterceptor() {
		return new ControllerInterceptor();
	}

	@Bean
	public ExceptionInfoConfig getExceptionInfoConfig() {
		return new ExceptionInfoConfig("exception/exception.yml");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
