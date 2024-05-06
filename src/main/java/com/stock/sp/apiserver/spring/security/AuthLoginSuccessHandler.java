package kr.co.dsi.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dsi.common.constant.ComConstantAuth;
import kr.co.dsi.common.dto.web.ComResponseDto;
import kr.co.dsi.common.exception.BizException;
import kr.co.dsi.common.exception.ExceptionInfoConfig;
import kr.co.dsi.common.login.dto.res.LoginUserDto;
import kr.co.dsi.common.login.dto.res.LoginUserSimpleInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 
 * @programName : AuthHandlerSuccess
 * @author : cwcho
 * @description : 인증 성공시 처리
 */
@Slf4j
@Component
public class AuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Environment env;

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	private final String ADM_PAGE_DSI = "dsi-web";
	private final String ADM_PAGE = "admin";
	
//	@Autowired
//	private SessionConfig sessionConfig;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		String referer = request.getHeader("Referer");

		response.setStatus(HttpServletResponse.SC_OK);

		LoginUserDto lginUsr = (LoginUserDto) authentication.getPrincipal();

		LoginUserSimpleInfoDto lginUsrResDto = lginUsr.getLoginUserSimpleInfoDto();

		// 관리자 페이지 로그인시 역할 확인
		if((referer.contains(ADM_PAGE) || referer.contains(ADM_PAGE_DSI)) && !ComConstantAuth.ROLE_TOP.equals(lginUsr.getRole())) {
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
		} else {
			ComResponseDto<LoginUserSimpleInfoDto> comResponseDto = new ComResponseDto<LoginUserSimpleInfoDto>(lginUsrResDto);
			// 성공시 응답을 정의(html 또는 json)
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(objectMapper.writeValueAsString(comResponseDto));
			response.getWriter().flush();
		}
	}

}
