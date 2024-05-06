package kr.co.dsi.spring.security;

import kr.co.dsi.common.constant.ComConstantAuth;
import kr.co.dsi.common.dao.CommonDao;
import kr.co.dsi.common.exception.BizException;
import kr.co.dsi.common.login.dto.req.LoginReqDto;
import kr.co.dsi.common.login.dto.res.LoginUserDto;
import kr.co.dsi.common.login.dto.res.LoginUserMenuDto;
import kr.co.dsi.common.login.dto.res.LoginUserRoleDto;
import kr.co.dsi.system.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @programName : AuthProvider
 * @author : cwcho
 * @description : 인증 처리
 */
@Component
@Slf4j
public class AuthLoginProvider implements AuthenticationProvider {
	private final String NAME_SPACE = "kr.co.dsi.common.login.";
	/**
	 * 역할 서비스
	 */
	@Autowired
	RoleService roleService;
	/**
	 * 공통
	 */
	@Autowired
	CommonDao commonDao;
	/**
	 * 비밀번호 암호화 모듈
	 */
	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${spring.security.login.secretKey}")
	private String aes256SecretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 1. 사용자가 입력한 id, pwd
		LoginReqDto loginReqDto = new LoginReqDto();
		String userId = authentication.getName();
		String password = authentication.getCredentials().toString();
		loginReqDto.setUserId(userId);
		loginReqDto.setPassword(password);

		// db에서 암호화된 비밀번호 조회
		String encPassword = commonDao.select(NAME_SPACE.concat("selectUserEncPswd"), userId);
		if (encPassword == null) {
			throw new BizException("login_id_pw_not_valid");
		}

		LoginUserDto loginUser = null;
		// 암호 체크
		if (passwordEncoder.matches(password, encPassword)) {
			// 인증 성공
			loginUser = commonDao.select(NAME_SPACE.concat("selectUser"), userId);
		} else {
			throw new BizException("login_id_pw_not_valid");
		}

		LoginUserDto loginUserByCustomer = null;
		UsernamePasswordAuthenticationToken result = null;

		if (loginUser != null) {
			// 2. 권한 정보 세팅
//			String role = ComConstantAuth.ROLE_TOP;
			LoginUserRoleDto loginUserRoleDto = commonDao.select(NAME_SPACE.concat("selectLoginUserRole"), loginReqDto);
			String role = loginUserRoleDto.getRoleId();
			String roleName = loginUserRoleDto.getRoleName();

			List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
			// 모든 사용자에게 기본 역할 할당
			grantedAuthorityList.add(new SimpleGrantedAuthority(ComConstantAuth.ROLE_DEFAULT));
			grantedAuthorityList.add(new SimpleGrantedAuthority(role));

			// 3. 메뉴 정보 세팅
			loginReqDto.setRoleId(role);
			List<LoginUserMenuDto> menuList = commonDao.selectList(NAME_SPACE.concat("selectLoginUserMenuList"), loginReqDto);
			loginUser.setRole(role);
			loginUser.setRoleName(roleName);
			loginUser.setMenuScreenInfo(menuList);

			// 4. 세션에 비밀번호 및 사용자 정보 입력
			result = new UsernamePasswordAuthenticationToken(loginUser, password, grantedAuthorityList);
		} else {
			throw new BizException("login_id_pw_not_valid");
		}

		// null == 인증세션 X
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
