package com.stock.sp.apiserver.system.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.common.exception.ExceptionInfoConfig;
import com.stock.sp.apiserver.spring.ComResponseEntity;
import com.stock.sp.apiserver.spring.session.SessionAttributeManager;
import com.stock.sp.apiserver.system.user.dto.RoleUserMapgDto;
import com.stock.sp.apiserver.system.user.dto.req.*;
import com.stock.sp.apiserver.system.user.dto.res.RoleUserReadListResDto;
import com.stock.sp.apiserver.system.user.dto.res.UserReadListResDto;
import com.stock.sp.apiserver.system.user.dto.res.UserReadResDto;
import com.stock.sp.apiserver.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;

/**
 *
 * @programName : UserController
 * @author : thdtkddjs
 * @description : 사용자 정보 관리
 */
@CrossOrigin
@RestController
@Tag(name = "08. 사용자 관리", description = "사용자 관리")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	/**
	 * 사용자 서비스
	 */
	@Autowired
	private UserService userService;

	/**
	 * 사용자 목록 조회
	 * 
	 * @methodName : selectUserList
	 * @author : thdtkddjs
	 * @param userSearchReqDto
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "사용자 조회", description = "사용자 조회")
	@Parameters({
			@Parameter(name = "searchType", description = "검색 조건 <br><br>ALL : 전체 " +
					"<br><br> USER_NM : 계정 명 <br><br> USER_ID : 사용자 아이디(사번)", example = "ALL", schema = @Schema(type = "string")),
			@Parameter(name = "keyword", description = "검색어", schema = @Schema(type = "string")),
			@Parameter(name = "statCd", description = "활성화 여부 <br><br>ALL : 전체 " +
					"<br><br> ACTIVE : 활성화 <br><br> INACTIVE : 비활성화", example = "ALL", schema = @Schema(type = "string")),
			@Parameter(name = "currentPage", description = "현재 페이지(옵션, 빈값일 경우 1 기본세팅)", schema = @Schema(type = "integer")),
			@Parameter(name = "limit", description = "페이지 사이즈(옵션, 빈값일 경우 10 기본세팅)", schema = @Schema(type = "integer")) })
	@GetMapping()
	public ComResponseEntity<UserReadListResDto> selectUserList(
			@Parameter(hidden = true) UserSearchReqDto userSearchReqDto) throws Exception {
		UserReadListResDto result = userService.selectUserList(userSearchReqDto);

		return new ComResponseEntity<>(new ComResponseDto<>(result), HttpStatus.OK);
	}

	/**
	 * 사용자 상세 조회
	 *
	 * @methodName : selectUser
	 * @author : thdtkddjs
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "사용자 상세 조회", description = "사용자 상세 조회")
	@Parameters({ @Parameter(name = "userId", description = "사용자 아이디(필수)") })
	@GetMapping(value = "/{userId}")
	public ComResponseEntity<UserReadResDto> selectUser(
			HttpServletRequest request, @PathVariable(value = "userId") String userId)
			throws Exception {
		UserReadResDto userReadResDto = userService.selectUserOne(userId);

		ComResponseDto<UserReadResDto> result = new ComResponseDto<UserReadResDto>();
		result.setBody(userReadResDto);
		return new ComResponseEntity<UserReadResDto>(result, HttpStatus.OK);
	}

	/**
	 *
	 * @methodName : insertUser
	 * @author : thdtkddjs
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 사용자 등록
	 */
	@Operation(summary = "사용자 등록", description = "사용자 등록(Schemas > UserCreateReqDto 참고)")
	@PostMapping()
	public ComResponseEntity<Void> insertUser(HttpServletRequest request,
			@Valid @RequestBody UserCreateReqDto userCreateReqDto) throws Exception {

		userCreateReqDto.setRegUserId(SessionAttributeManager.getLoginUserId());

		userService.insertUser(userCreateReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.CREATED);
	}

	/**
	 *
	 * @methodName : updateUser
	 * @author : cwcho
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 사용자 수정
	 */
	@Operation(summary = "사용자 수정", description = "사용자 수정(Schemas > UserUpdateReqDto 참고)")
	@Parameters({ @Parameter(name = "userId", description = "사용자 아이디") })
	@PutMapping(value = "/{userId}")
	public ComResponseEntity<Void> updateUser(
			HttpServletRequest request,
			@Valid @RequestBody UserUpdateReqDto userUpdateReqDto,
			@PathVariable(value = "userId") String userId) throws Exception {

		userUpdateReqDto.setUserId(userId);
		userUpdateReqDto.setUpdUserId(SessionAttributeManager.getLoginUserId());

		userService.updateUser(userUpdateReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 *
	 * @methodName : updateUserPwd
	 * @author : cwcho
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 사용자 비밀번호 수정
	 */
	@Operation(summary = "사용자 비밀번호 수정", description = "관리자에 의한 변경이므로 기존 비밀번호 확인 절차 생략")
	@Parameters({ @Parameter(name = "userId", description = "사용자 아이디") })
	@PutMapping(value = "/upd-pwd/{userId}")
	public ComResponseEntity<Void> updateUserPwd(@Valid @RequestBody UserUpdatePswdReqDto userUpdatePwdReqDto,
			@PathVariable(value = "userId") String userId) throws Exception {

		userUpdatePwdReqDto.setUserId(userId);
		userUpdatePwdReqDto.setUpdUserId(SessionAttributeManager.getLoginUserId());

		userService.updateUserPswd(userUpdatePwdReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 *
	 * @methodName : removeUser
	 * @author : thdtkddjs
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 사용자 삭제
	 */
	@Operation(summary = "사용자 삭제", description = "사용자 삭제")
	@Parameters({ @Parameter(name = "userId", description = "사용자 아이디") })
	@DeleteMapping(value = "/{userId}")
	public ComResponseEntity<Void> deleteUser(HttpServletRequest request, @PathVariable String userId) {

		userService.deleteUser(userId);
		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 * 권한 관리 목록 조회
	 *
	 * @param roleUserSearchReqDto
	 * @return
	 * @throws Exception
	 */
	@Operation(summary = "권한 관리 목록 조회", description = "권한 관리 목록 조회")
	@Parameters({
			@Parameter(name = "searchType", description = "검색 조건 <br><br>ALL : 전체 " +
					"<br><br> userId : 계정 ID <br><br> userNm : 계정 명", example = "ALL", schema = @Schema(type = "string")),
			@Parameter(name = "keyword", description = "검색어", schema = @Schema(type = "string")),
			@Parameter(name = "currentPage", description = "현재 페이지(옵션, 빈값일 경우 1 기본세팅)", schema = @Schema(type = "integer")),
			@Parameter(name = "limit", description = "페이지 사이즈(옵션, 빈값일 경우 10 기본세팅)", schema = @Schema(type = "integer")) })
	@GetMapping(value = "/role-user")
	public ComResponseEntity<RoleUserReadListResDto> selectRoleUserList(
			@Parameter(hidden = true) RoleUserSearchReqDto roleUserSearchReqDto) throws Exception {
		RoleUserReadListResDto result = userService.selectRoleUserList(roleUserSearchReqDto);

		return new ComResponseEntity<>(new ComResponseDto<>(result), HttpStatus.OK);
	}

	/**
	 *
	 * @methodName : updateRoleUser
	 * @author : thdtkddjs
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 사용자 권한 업데이트
	 */
	@Operation(summary = "사용자 권한 업데이트(현재 미사용)", description = "사용자 권한 업데이트(역할ID 부여)")
	@PutMapping(value = "/role-user")
	public ComResponseEntity<Void> updateRoleUser(@Valid @RequestBody List<RoleUserMapgDto> data) {

		userService.updateRoleUser(data);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}
}
