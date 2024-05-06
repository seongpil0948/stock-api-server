package com.stock.sp.apiserver.spring.session;

import com.stock.sp.apiserver.common.login.dto.res.LoginUserDto;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 세션 정보 관리
 *
 * @programName : SessionAttributeManager
 * @author : 이성진
 * @description :
 */
public class SessionAttributeManager {
	private final static String STR_SEPARATOR = ";";

	/**
	 * 로그인 사용자 정보
	 *
	 * @methodName : getlginUsr
	 * @author : 이성진
	 * @return : LginUsr
	 * @description :
	 */
	public static LoginUserDto getLoginUserInfo() {
		Object userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LoginUserDto lginUsr = null;
		if (userInfo instanceof LoginUserDto) {
			lginUsr = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return lginUsr;
	}

	/**
	 * 로그인 사용자명 조회
	 *
	 * @methodName : getLoginUserName
	 * @author : 이성진
	 * @return : String
	 * @description :
	 */
	public static String getLoginUserName() {
		return getLoginUserInfo().getUserName();
	}

	/**
	 * 로그인 사용자 아이디 조회
	 *
	 * @methodName : getLoginUserId
	 * @author : 이성진
	 * @return : String
	 * @description :
	 */
	public static String getLoginUserId() {
		return getLoginUserInfo().getUserId();
	}

}
