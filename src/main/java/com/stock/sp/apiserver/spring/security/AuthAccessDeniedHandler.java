package kr.co.dsi.spring.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dsi.common.dto.web.ComResponseDto;
import kr.co.dsi.common.exception.ExceptionInfoConfig;
import kr.co.dsi.spring.session.SessionAttributeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @programName : AuthAccessDeniedHandler
 * @author : cwcho
 * @description : 권한이 없는 url에 접근할 경우
 */
@Slf4j
@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException ade) throws IOException, ServletException {

		log.info("##############################################");
		log.info("# ** 접근 권한 없음 ** ");
		log.info("# user_id={}", SessionAttributeManager.getLoginUserId());
		log.info("# date={}", new Date());
		log.info("# method={}, url= {}", request.getMethod(), request.getRequestURL());
		log.info("##############################################");

		ComResponseDto<String> comResponseDto = new ComResponseDto<String>();
		Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo("permission_denied");
		int statusCode = (Integer) exceptionInfo.get("status");
		String msg = (String) exceptionInfo.get("desc");
		Integer code = (Integer) exceptionInfo.get("code");
		comResponseDto.setHttpStatusCode(statusCode);
		comResponseDto.setCode(code.toString());
		comResponseDto.setMessage(msg);
		if (exceptionInfo == null) {
			comResponseDto.setBody("권한이 없습니다. 계속 시도할경우, 관리자가 추적합니다.");
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
