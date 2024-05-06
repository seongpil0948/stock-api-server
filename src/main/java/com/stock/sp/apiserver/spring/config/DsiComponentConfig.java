package com.stock.sp.apiserver.spring.config;

import com.stock.sp.apiserver.common.exception.ExceptionInfoConfig;
import com.stock.sp.apiserver.common.utils.ApplicationContextManager;
import com.stock.sp.apiserver.spring.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = "com.stock.sp")
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
