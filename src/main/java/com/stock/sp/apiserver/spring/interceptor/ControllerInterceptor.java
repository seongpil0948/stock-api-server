package com.stock.sp.apiserver.spring.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.common.exception.ExceptionInfoConfig;
import com.stock.sp.apiserver.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	@Value("${security.key}")
	private String AUTH_KEY;

	// @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		if (StringUtils.uriPatternMatches("/private/**", uri)) {
			String authKey = request.getHeader("authKey");
			log.info("uri {}, authKey {}", uri, authKey);

			if (AUTH_KEY.equals(authKey)) {
				log.info("success");
			} else {
				log.info("##############################################");
				log.info("# ** 접근 권한 없음 ** ");
				log.info("##############################################");
				accessDeniedCheck(request, response);
				return false;
			}
		}

		return true;
	}

	private void accessDeniedCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ComResponseDto<String> comResponseDto = new ComResponseDto<String>();
		Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo("permission_denied");
		int statusCode = (Integer) exceptionInfo.get("status");
		String msg = (String) exceptionInfo.get("desc");
		Integer code = (Integer) exceptionInfo.get("code");
		comResponseDto.setHttpStatusCode(statusCode);
		comResponseDto.setCode(code.toString());
		comResponseDto.setMessage(msg);
		// if (exceptionInfo == null) {
		// comResponseDto.setBody("권한이 없습니다.");
		// } else {
		comResponseDto.setBody(msg);
		// }

		// 응답을 정의(html 또는 json)
		response.setStatus(statusCode);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(objectMapper.convertValue(comResponseDto, ComResponseDto.class));
		response.getWriter().flush();
	}
}