package kr.co.dsi.spring.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @programName : AuthHandlerSuccess
 * @author : cwcho
 * @description : 로그아웃 처리
 */
@Component
public class AuthLogoutHandler implements LogoutSuccessHandler {


	@Value("${spring.security.logout.successUrl}")
	private String logoutSuccessUrl;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// 인증 세션이 있을 경우만..
		if (authentication != null && authentication.getDetails() != null) {
			try {
				// 세션 만료
				request.getSession().invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);

		// 로그아웃 만료 후 이동할 페이지 지정이 있을경우, redirection
		if (!StringUtils.isEmpty(this.logoutSuccessUrl)) {
			response.sendRedirect(this.logoutSuccessUrl);
		}
	}

}
