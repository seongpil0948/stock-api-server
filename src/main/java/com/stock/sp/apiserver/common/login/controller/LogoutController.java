package kr.co.dsi.common.login.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dsi.common.dto.web.ComResponseDto;
import kr.co.dsi.spring.ComResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@Tag(name = "01. 로그아웃", description = "로그아웃")
@RequestMapping("/logout")
public class LogoutController {

	@Operation(summary = "로그아웃", description = "로그아웃")
	@GetMapping
	public ComResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/**
		 * 로그인 컨트롤러 관련 SWAGGER 표현을 위해 만듬.
		 * 
		 * !!! 삭제 불가 !!!
		 * 
		 */

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}
}
