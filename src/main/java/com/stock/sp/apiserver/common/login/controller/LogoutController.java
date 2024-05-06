package com.stock.sp.apiserver.common.login.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.spring.ComResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
