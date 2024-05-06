package com.stock.sp.apiserver.spring.config;

import com.stock.sp.apiserver.spring.interceptor.ControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProgramName : SwaggerWebMvcConfig
 * @Author : LaVega
 * @Version : 1.0
 * @Description :
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ControllerInterceptor controllerInterceptor;

	// private final String baseUrl;
	//
	// public WebMvcConfig(String baseUrl) {
	// this.baseUrl = baseUrl;
	// }
	//
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
	// registry.addResourceHandler(baseUrl + "/swagger-ui/**")
	// .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
	// .resourceChain(false);
	// }
	//
	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// registry.addViewController(baseUrl + "/swagger-ui/")
	// .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	// }

	/**
	 * 인터셉터 적용
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(controllerInterceptor) // 대상 인터셉터 클래스
				.excludePathPatterns("/swagger-ui/**", "/api-docs/**", "/login/**");
	}
}
