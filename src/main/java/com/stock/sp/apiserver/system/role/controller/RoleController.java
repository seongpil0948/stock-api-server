package com.stock.sp.apiserver.system.role.controller;

import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.common.exception.BizException;
import com.stock.sp.apiserver.spring.ComResponseEntity;
import com.stock.sp.apiserver.system.role.dto.req.*;
import com.stock.sp.apiserver.system.role.dto.res.RoleMenuScrnMapgReadListResDto;
import com.stock.sp.apiserver.system.role.dto.res.RoleMenuScrnMapgReadResDto;
import com.stock.sp.apiserver.system.role.dto.res.RoleReadListResDto;
import com.stock.sp.apiserver.system.role.dto.res.RoleReadResDto;
import com.stock.sp.apiserver.system.role.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;

/**
 * 
 * @programName : RoleCtrl
 * @author : cwcho
 * @description : 역할 정보 관리
 */
@CrossOrigin
@RestController
@Tag(name = "03. 역할 관리", description = "역할 관리")
@RequestMapping("/roles")
public class RoleController {
	private final Logger logger = LogManager.getLogger(getClass());
	/**
	 * 역할 서비스
	 */
	@Autowired
	RoleService roleSvc;

	/**
	 * 
	 * @methodName : getRoleList
	 * @author : cwcho
	 * @param searchType
	 * @param useYn
	 * @param currentPage
	 * @param limit
	 * @return
	 * @throws Exception
	 * @return : ResponseEntity<ComResponseDto<List<RoleReadResDto>>>
	 * @description : 역할 목록 조회
	 */
	@Operation(summary = "역할 목록 조회", description = "역할 목록 조회")
	@Parameters({
			@Parameter(name = "searchType", description = "검색 조건 <br><br>ALL : 전체 <br><br> ROLE_ID : 역할 ID <br><br> ROLE_NM : 역할 명 ", example = "ALL"),
			@Parameter(name = "keyword", description = "검색어"),
			@Parameter(name = "useYn", description = "사용 여부<br><br>ALL | Y | N", example = "ALL"),
			@Parameter(name = "currentPage", description = "현재 페이지(옵션, 빈값일 경우 1 기본세팅)"),
			@Parameter(name = "limit", description = "페이지 사이즈(옵션, 빈값일 경우 10 기본세팅)") })
	@GetMapping()
	public ComResponseEntity<RoleReadListResDto> getRoleList(
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "useYn", required = false) String useYn,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "limit", required = false) Integer limit) throws Exception {
		RoleReadReqDto roleReadReqDto = new RoleReadReqDto();
		roleReadReqDto.setUseYn(useYn);
		roleReadReqDto.setSearchType(searchType);
		roleReadReqDto.setKeyword(keyword);
		roleReadReqDto.setCurrentPage(currentPage);
		roleReadReqDto.setLimit(limit);

		// 전체 카운트 조회
		int totalCount = roleSvc.selectRoleCount(roleReadReqDto);
		int totalPage = totalCount / roleReadReqDto.getLimit();
		if (totalCount % roleReadReqDto.getLimit() > 0) {
			totalPage++;
		}

		List<RoleReadResDto> roleReadResDtoList = roleSvc.selectRoleList(roleReadReqDto);
		RoleReadListResDto roleReadListResDto = new RoleReadListResDto();
		roleReadListResDto.setTotalCount(totalCount);
		roleReadListResDto.setTotalPage(totalPage);
		roleReadListResDto.setData(roleReadResDtoList);
		roleReadListResDto.setCurrentPage(roleReadReqDto.getCurrentPage());
		roleReadListResDto.setLimit(roleReadReqDto.getLimit());
		return new ComResponseEntity<>(new ComResponseDto<>(roleReadListResDto), HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : getRole
	 * @author : cwcho
	 * @param roleId : RoleID
	 * @return
	 * @throws Exception
	 * @return : ResponseEntity<ComResponseDto<RoleReadResDto>>
	 * @description : 역할 상세 조회
	 */
	@Operation(summary = "역할 상세 조회", description = "역할 상세 조회")
	@Parameters({ @Parameter(name = "roleId", description = "역할 아이디(필수)") })
	@GetMapping(value = "/{roleId}")
	public ComResponseEntity<RoleReadResDto> getRole(@PathVariable(value = "roleId", required = true) String roleId)
			throws Exception {
		RoleReadReqDto roleReadReqDto = new RoleReadReqDto();
		roleReadReqDto.setRoleId(roleId);

		RoleReadResDto roleReadResDto = roleSvc.selectRoleOne(roleReadReqDto);

		ComResponseDto<RoleReadResDto> result = new ComResponseDto<RoleReadResDto>();
		result.setBody(roleReadResDto);
		return new ComResponseEntity<RoleReadResDto>(result, HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : regRole
	 * @author : cwcho
	 * @param roleCreateReqDto : roleCreateReqDto
	 * @return void
	 * @throws Exception any
	 * @return : ResponseEntity<ComResponseDto<Void>> respose
	 * @description : 역할 등록
	 */
	@Operation(summary = "역할 등록", description = "역할 등록(Schemas > RoleCreateReqDto 참고)")
	@Parameters({})
	@PostMapping()
	public ComResponseEntity<Void> regRole(@Valid @RequestBody RoleCreateReqDto roleCreateReqDto) throws Exception {
		// 역할 정보 등록
		roleSvc.insertRole(roleCreateReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : updateRole
	 * @author : cwcho
	 * @param roleUpdateReqDto
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 역할 수정
	 */
	@Operation(summary = "역할 수정", description = "역할 수정(Schemas > RoleUpdateReqDto 참고)")
	@Parameters({})
	@PutMapping(value = "/{roleId}")
	public ComResponseEntity<Void> updateRole(@Valid @RequestBody RoleUpdateReqDto roleUpdateReqDto,
			@PathVariable(value = "roleId", required = true) String roleId) throws Exception {
		// 역할 정보 수정
		roleSvc.updateRole(roleId, roleUpdateReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : removeRole
	 * @author : cwcho
	 * @return
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 역할 삭제
	 */
	@Operation(summary = "역할 삭제", description = "역할 삭제")
	@DeleteMapping(value = "/{roleId}")
	public ComResponseEntity<Void> removeRole(@PathVariable(value = "roleId", required = true) String roleId) {
		// 역할 정보 삭제
		roleSvc.deleteRole(roleId);
		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : getRoleMenuScrnMapgList
	 * @author : cwcho
	 * @param roleId
	 * @param menuId
	 * @param screenId
	 * @return
	 * @throws Exception
	 * @return : ComResponseEntity<RoleMenuScrnMapgReadListResDto>
	 * @description : 역할-메뉴-화면 매핑 목록 조회
	 */
	@Operation(summary = "역할-메뉴-화면 매핑 목록 조회", description = "역할-메뉴-화면 매핑 목록 조회")
	@Parameters({ @Parameter(name = "roleId", description = "역할 아이디(옵션)(equal 검색)"),
			@Parameter(name = "menuId", description = "메뉴 명(옵션)(equal 검색)"),
			@Parameter(name = "screenId", description = "화면 명(옵션)(equal 검색)") })
	@GetMapping(value = "/role-menu-scrn")
	public ComResponseEntity<RoleMenuScrnMapgReadListResDto> getRoleMenuScrnMapgList(
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "menuId", required = false) String menuId,
			@RequestParam(value = "screenId", required = false) String screenId) throws Exception {

		RoleMenuScrnMapgReadReqDto roleMenuScrnMapgReadReqDto = new RoleMenuScrnMapgReadReqDto();
		roleMenuScrnMapgReadReqDto.setRoleId(roleId);
		roleMenuScrnMapgReadReqDto.setMenuId(menuId);
		roleMenuScrnMapgReadReqDto.setScreenId(screenId);

		// 전체 카운트 조회
		int totalCount = roleSvc.selectRoleMenuScrnMapgCount(roleMenuScrnMapgReadReqDto);
		List<RoleMenuScrnMapgReadResDto> roleMenuScrnMapgReadResDtoList = roleSvc
				.selectRoleMenuScrnMapg(roleMenuScrnMapgReadReqDto);
		RoleMenuScrnMapgReadListResDto roleMenuScrnMapgReadListResDto = new RoleMenuScrnMapgReadListResDto();
		roleMenuScrnMapgReadListResDto.setData(roleMenuScrnMapgReadResDtoList);
		roleMenuScrnMapgReadListResDto.setTotalCount(totalCount);
		return new ComResponseEntity<>(new ComResponseDto<>(roleMenuScrnMapgReadListResDto), HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : insertRoleMenuScrnMapg
	 * @author : cwcho
	 * @param request
	 * @param roleMenuScrnMapgCreateReqDto
	 * @param roleId
	 * @return
	 * @throws Exception
	 * @return : ResponseEntity<ComResponseDto<Void>>
	 * @description : 역할-메뉴-화면 매핑 정보 등록
	 */
	@Operation(summary = "역할아이디 기준 모든 역할-메뉴-화면 매핑 정보를 갱신한다.(전체 삭제 후 등록)", description = "역할아이디 기준 모든 역할-메뉴-화면 매핑 정보를 갱신한다.(전체 삭제 후 등록)<br/>(Schemas > RoleMenuScrnMapgCreateReqDto 참고)")
	@Parameters({ @Parameter(name = "roleId", description = "역할 아이디") })
	@PutMapping("/role-menu-scrn/{roleId}")
	public ComResponseEntity<Void> updateRoleMenuScrnMapg(HttpServletRequest request,
			@PathVariable(value = "roleId", required = true) String roleId,
			@Valid @RequestBody RoleMenuScrnMapgUpdateReqDto roleMenuScrnMapgCreateReqDto) throws Exception {

		// 역할-메뉴-화면 정보 등록
		roleSvc.updateRoleMenuScrnMapgAll(roleId, roleMenuScrnMapgCreateReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	@Operation(summary = "역할-메뉴-화면 매핑 단건 삭제", description = "역할-메뉴-화면 매핑 단건 삭제")
	@Parameters({ @Parameter(name = "roleId", description = "역할 아이디") })
	@DeleteMapping(value = "/role-menu-scrn/{roleId}/{menuId}")
	public ComResponseEntity<Void> removeRoleMenuScrn(@PathVariable(value = "roleId", required = true) String roleId,
			@PathVariable(value = "menuId", required = true) String menuId) {

		// 역할 정보 삭제
		if (roleSvc.deleteRoleMenuScrnMapg(roleId, menuId) < 1) {
			throw new BizException("fail_remove");
		}

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}

	/**
	 * 
	 * @methodName : insertRoleUsrMapg
	 * @author : cwcho
	 * @param request
	 * @param roleId
	 * @param roleUsrMapgUpdateReqDto
	 * @return
	 * @throws Exception
	 * @return : ComResponseEntity<Void>
	 * @description : 역할아이디 기준 역할-사용자 매핑 정보 갱신
	 */
	@Operation(summary = "역할아이디 기준 역할-사용자 매핑 정보를 갱신한다.(전체 삭제 후 등록)", description = "역할아이디 기준 역할-사용자 매핑 정보를 갱신한다.(전체 삭제 후 등록)")
	@Parameters({ @Parameter(name = "roleId", description = "역할 아이디") })
	@PutMapping("/role-user/{roleId}")
	public ComResponseEntity<Void> insertRoleUsrMapg(HttpServletRequest request,
			@PathVariable(value = "roleId", required = true) String roleId,
			@Valid @RequestBody RoleUsrMapgUpdateReqDto roleUsrMapgUpdateReqDto) throws Exception {

		// 역할-메뉴-화면 정보 등록(신규,삭제건이 없을수도 있으므로, 처리 건수는 체크하지 않는다.)
		roleSvc.updateRoleUsrMapg(roleId, roleUsrMapgUpdateReqDto);

		ComResponseDto<Void> result = new ComResponseDto<Void>();
		return new ComResponseEntity<Void>(result, HttpStatus.OK);
	}
}
