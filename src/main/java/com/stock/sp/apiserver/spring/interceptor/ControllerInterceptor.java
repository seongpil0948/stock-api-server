package kr.co.dsi.spring.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dsi.common.dto.web.ComResponseDto;
import kr.co.dsi.common.exception.BizException;
import kr.co.dsi.common.exception.ExceptionInfoConfig;
import kr.co.dsi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	@Value("${ai.external.key}")
	private String AUTH_KEY;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		if (StringUtils.uriPatternMatches("/ext/**", uri)) {
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

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
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
		if (exceptionInfo == null) {
			comResponseDto.setBody("권한이 없습니다.");
		} else {
			comResponseDto.setBody(msg);
		}

		// 응답을 정의(html 또는 json)
		response.setStatus(statusCode);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(objectMapper.convertValue(comResponseDto, ComResponseDto.class));
		response.getWriter().flush();
	}
}