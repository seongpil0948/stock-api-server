package com.stock.sp.apiserver.common.login.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.spring.ComResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@Tag(description = "Swagger 표현을 위한 로그인 api 껍데기", name = "00. 로그인")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private Environment env;

	@Operation(summary = "로그인", description = "로그인</br></br> ** 테스트 계정 : " +
			"</br></br>시스템 관리자: {</br>" +
			"  \"userId\": \"system\",</br>" +
			"  \"password\": \"1234qwer!!\"</br>}")
	@Parameters({
			@Parameter(name = "info1", description = "사용자 아이디", required = true, in = ParameterIn.QUERY),
			@Parameter(name = "info2", description = "사용자 비밀번호", required = true, in = ParameterIn.QUERY)
	})
	@PostMapping
	public ComResponseEntity<Void> prevLogin(HttpServletRequest request,
			@RequestParam(value = "info1", required = true) String info1,
			@RequestParam(value = "info2", required = true) String info2) throws Exception {
		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}
}
