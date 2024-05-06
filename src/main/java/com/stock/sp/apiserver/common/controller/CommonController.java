package com.stock.sp.apiserver.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.common.login.dto.res.LoginUserDto;
import com.stock.sp.apiserver.common.login.dto.res.LoginUserSimpleInfoDto;
import com.stock.sp.apiserver.common.service.CommonService;
import com.stock.sp.apiserver.spring.ComResponseEntity;
import com.stock.sp.apiserver.spring.session.SessionAttributeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@Tag(name = "09. 공통 기능", description = "공통 기능")
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Operation(summary = "세션 정보 조회", description = "세션 정보 조회")
    @GetMapping("/session")
    public ComResponseEntity<LoginUserSimpleInfoDto> getSession(HttpServletRequest request) throws Exception {
        // 로그인 사용자 객체
        LoginUserDto lginUsr = SessionAttributeManager.getLoginUserInfo();

        // API로 제공할, 세션 정보(간소화된 정보)
        LoginUserSimpleInfoDto lginUsrResDto = lginUsr.getLoginUserSimpleInfoDto();

        ComResponseDto<LoginUserSimpleInfoDto> result = new ComResponseDto<LoginUserSimpleInfoDto>();
        result.setBody(lginUsrResDto);
        return new ComResponseEntity<LoginUserSimpleInfoDto>(result, HttpStatus.OK);
    }

}
