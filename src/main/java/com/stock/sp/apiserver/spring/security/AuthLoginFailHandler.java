package kr.co.dsi.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.dsi.common.dto.web.ComResponseDto;
import kr.co.dsi.common.dto.web.ComResultDto;
import kr.co.dsi.common.exception.ExceptionInfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : cwcho
 * @programName : AuthLoginFailHandler
 * @description : 인증 실패시 처리
 */
@Component
public class AuthLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExceptionInfoConfig exceptionInfoConfig;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ComResultDto authErrorResultInfo = exceptionInfoConfig.getExceptionInfoResult("login_id_pw_not_valid");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ComResponseDto<String> comResponseDto = new ComResponseDto<String>();
        comResponseDto.setBody("");
        comResponseDto.setCode(authErrorResultInfo.getCode());
        comResponseDto.setHttpStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
        comResponseDto.setStatus(authErrorResultInfo.getStatus());
        comResponseDto.setMessage(authErrorResultInfo.getMessage());

		// 성공시 응답을 정의(html 또는 json)
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(objectMapper.convertValue(comResponseDto, ComResponseDto.class));
        response.getWriter().flush();
    }
}
