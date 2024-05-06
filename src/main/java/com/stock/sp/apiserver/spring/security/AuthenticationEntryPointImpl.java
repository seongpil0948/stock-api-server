package kr.co.dsi.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dsi.common.dto.web.ComResponseDto;
import kr.co.dsi.common.exception.ExceptionInfoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @programName : AuthenticationEntryPointImpl
 * @author : cwcho
 * @description : 로그인 되지 않은 상태에서 url에 접근할 경우
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {
		log.info("######## 로그인 필요 #######");
		log.info("# date={}", new Date());
		log.info("# method={}, url= {}", request.getMethod(), request.getRequestURL());
		log.info("#########################");

		Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo("login_required");
		int statusCode = (Integer) exceptionInfo.get("status");
		String msg = (String) exceptionInfo.get("desc");
		Integer code = (Integer) exceptionInfo.get("code");
		ComResponseDto<String> comResponseDto = new ComResponseDto<String>();
		comResponseDto.setHttpStatusCode(statusCode);
		comResponseDto.setMessage(msg);
		comResponseDto.setCode(code.toString());
		if (exceptionInfo == null) {
			comResponseDto.setBody("로그인이 필요한 서비스입니다.");
		} else {
			comResponseDto.setBody(msg);
		}

		// 성공시 응답을 정의(html 또는 json)
		response.setStatus(statusCode);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(objectMapper.convertValue(comResponseDto, ComResponseDto.class));
		response.getWriter().flush();
	}
}
