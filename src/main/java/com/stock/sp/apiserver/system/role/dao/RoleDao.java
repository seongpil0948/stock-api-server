package kr.co.dsi.system.role.dao;

import kr.co.dsi.system.role.dto.RoleApiAuthMapg;
import kr.co.dsi.system.role.dto.RoleMenuScrnMapg;
import kr.co.dsi.system.role.dto.RoleUsrDto;
import kr.co.dsi.system.role.dto.req.ApiAuthReqDto;
import kr.co.dsi.system.role.dto.req.RoleMenuScrnMapgReadReqDto;
import kr.co.dsi.system.role.dto.req.RoleReadReqDto;
import kr.co.dsi.system.role.dto.res.ApiAuthResDto;
import kr.co.dsi.system.role.dto.res.MenuApiMapgResDto;
import kr.co.dsi.system.role.dto.res.RoleMenuScrnMapgReadResDto;
import kr.co.dsi.system.role.dto.res.RoleReadResDto;
import kr.co.dsi.system.role.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @programName : RoleDao
 * @author : cwcho
 * @description : 역할관리
 */
@Mapper
@Repository
public interface RoleDao {

	/**
	 *
	 * @methodName : getLginUsrRole
	 * @author : cwcho
	 * @param usrId
	 * @param applicationName
	 * @return
	 * @return : List<RoleUsrDto>
	 * @description : 사용자에 할당된 역할 조회
	 */
	List<RoleUsrDto> getLginUsrRole(String usrId, String applicationName);

	/**
	 *
	 * @methodName : getRoleMenuScrn
	 * @author : cwcho
	 * @return : List<MenuScrnResDto>
	 * @description : 역할에 할당된 메뉴-화면 정보 조회(관리자페이지 용도)
	 */
	List<RoleMenuScrnMapg> getRoleMenuScrn(List<RoleUsrDto> roleList);

	/**
	 *
	 * @methodName : getRoleMenuScrn
	 * @author : cwcho
	 * @return : List<MenuScrnResDto>
	 * @description : 역할에 할당된 메뉴-화면 정보 조회(로그인 사용자 용도)
	 */
	List<RoleMenuScrnMapg> getRoleMenuScrnRoleUse(List<RoleUsrDto> roleList);

	/**
	 *
	 * @methodName : getRoleApiAuthMapg
	 * @author : cwcho
	 * @return : List<RoleMenuScrnMapg>
	 * @description : 스프링 시큐리티 관련 역할-api-권한 매핑 전체 정보 조회
	 */
	List<RoleApiAuthMapg> getRoleApiAuthMapgForSpringSecurity(String applicationName);

	List<RoleApiAuthMapg> getRoleApiMapgForSpringSecurity();

	/**
	 *
	 * @methodName : getRoleApiAuthMapg
	 * @author : cwcho
	 * @return : List<RoleMenuScrnMapg>
	 * @description : 역할-api-권한 매핑 정보 조회
	 */
	List<RoleApiAuthMapgEntity> getRoleApiAuthMapgBySelective(RoleApiAuthMapgEntity roleApiAuthMapgEntity);

	/*
	 * ###############################################
	 * ###############################################
	 */

	/**
	 *
	 * @methodName : selectRoleCount
	 * @author : cwcho
	 * @param roleReadReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할 목록 조회 카운트
	 */
	Integer selectRoleCount(RoleReadReqDto roleReadReqDto);

	/**
	 *
	 * @methodName : selectRoleList
	 * @author : cwcho
	 * @param roleReadReqDto
	 * @return
	 * @return : List<RoleReadResDto>
	 * @description : 역할 목록 조회
	 */
	List<RoleReadResDto> selectRoleList(RoleReadReqDto roleReadReqDto);

	/**
	 *
	 * @methodName : selectRole
	 * @author : cwcho
	 * @param roleReadReqDto
	 * @return
	 * @return : RoleReadResDto
	 * @description : 역할 목록 조회
	 */
	RoleReadResDto selectRole(RoleReadReqDto roleReadReqDto);

	/**
	 * @methodName : selectAssignMenuRoleList
	 * @author : "ChangGuenKim"
	 * @param roleReadReqDto
	 * @return
	 * @return : MenuApiMapgResDto
	 * @description : 메뉴 역할 목록 조회(관리자 용도)
	 */
	List<MenuApiMapgResDto> selectAssignMenuRoleList(RoleReadReqDto roleReadReqDto);

	/**
	 *
	 * @methodName : insertRole
	 * @author : cwcho
	 * @return : Integer
	 * @description : 역할 등록
	 */
	Integer insertRole(Role role);

	/**
	 *
	 * @methodName : updateRole
	 * @author : cwcho
	 * @return : Integer
	 * @description : 역할 수정
	 */
	Integer updateRole(Role role);

	/**
	 *
	 * @methodName : deleteRole
	 * @author : cwcho
	 * @return : Integer
	 * @description : 역할 삭제
	 */
	Integer deleteRole(Role role);

	/**
	 *
	 * @methodName : insertRoleApiAuthMapg
	 * @author : cwcho
	 * @param roleApiAuthMapgEntityList
	 * @return
	 * @return : Integer
	 * @description : 역할-API-권한 매핑 정보 등록(멀티건)
	 */
	Integer insertRoleApiAuthMapgList(List<RoleApiAuthMapgEntity> roleApiAuthMapgEntityList);

	/**
	 *
	 * @methodName : selectRoleMenuScrnMapg
	 * @author : cwcho
	 * @param roleMenuScrnMapgReadReqDto
	 * @return
	 * @return : List<RoleMenuScrnMapgReadResDto>
	 * @description : 역할-메뉴-화면 매핑 정보 조회(화면용도)
	 */
	List<RoleMenuScrnMapgReadResDto> selectRoleMenuScrnMapg(RoleMenuScrnMapgReadReqDto roleMenuScrnMapgReadReqDto);

	/**
	 *
	 * @methodName : getRoleMenuScrnMapgBySelective
	 * @author : cwcho
	 * @param roleMenuScrnMapgEntity
	 * @return
	 * @return : List<RoleMenuScrnMapgEntity>
	 * @description : 역할-메뉴-화면 매핑 정보 조회
	 */
	List<RoleMenuScrnMapgEntity> getRoleMenuScrnMapgBySelective(RoleMenuScrnMapgEntity roleMenuScrnMapgEntity);

	/**
	 *
	 * @methodName : selectRoleMenuScrnMapgCount
	 * @author : cwcho
	 * @param roleMenuScrnMapgReadReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할-메뉴-화면 매핑 정보 조회 카운트
	 */
	Integer selectRoleMenuScrnMapgCount(RoleMenuScrnMapgReadReqDto roleMenuScrnMapgReadReqDto);

	/**
	 *
	 * @methodName : insertRoleMenuScrnMapgList
	 * @author : cwcho
	 * @param record
	 * @return
	 * @return : Integer
	 * @description : 역할-메뉴-화면 매핑 정보 등록(멀티건)
	 */
	Integer insertRoleMenuScrnMapgList(List<RoleMenuScrnMapgEntity> record);

	/**
	 *
	 * @methodName : deleteRoleMenuScrnMapg
	 * @author : cwcho
	 * @return : Integer
	 * @description : 역할-메뉴-화면 매핑 정보 삭제
	 */
	Integer deleteRoleMenuScrnMapg(RoleMenuScrnMapgEntity roleMenuScrnMapgEntity);

	/**
	 *
	 * @methodName : deleteRoleMenuScrnMapgByRoleId
	 * @author : cwcho
	 * @param roleMenuScrnMapgEntity
	 * @return
	 * @return : Integer
	 * @description : 역할 아이디 기준으로 역할-메뉴-화면 매핑 정보 삭제
	 */
	Integer deleteRoleMenuScrnMapgByRoleId(String roleId);

	/**
	 *
	 * @methodName : deleteRoleApiAuthMapg
	 * @author : cwcho
	 * @param roleApiAuthMapgEntity
	 * @return
	 * @return : Integer
	 * @description : 역할-api- 권한 매핑 정보 삭제
	 */
	Integer deleteRoleApiAuthMapg(RoleApiAuthMapgEntity roleApiAuthMapgEntity);

	/**
	 *
	 * @methodName : deleteRoleApiAuthMapgByRoleId
	 * @author : cwcho
	 * @param roleId
	 * @return
	 * @return : Integer
	 * @description : 역할 아이디 기준으로 역할-api-권한 매핑 정보 삭제
	 */
	Integer deleteRoleApiAuthMapgByRoleId(String roleId);

	/**
	 *
	 * @methodName : insertRoleUsrMapg
	 * @author : cwcho
	 * @return : Integer
	 * @description : 역할-사용자 매핑 정보 멀티건 등록
	 */
	Integer insertRoleUsrMapgList(List<RoleUsrMapgEntity> roleUsrMapgEntity);


	/**
	 *
	 * @methodName : getRoleUsrMapngByRole
	 * @author : cwcho
	 * @param roleId
	 * @return
	 * @return : List<RoleUsrMapgEntity>
	 * @description : 역할기준 역할-사용자 매핑 정보 조회
	 */
	List<RoleUsrMapgEntity> getRoleUsrMapngByRole(String roleId);

	/**
	 *
	 * @methodName : getRoleUsrMapngByUsr
	 * @author : cwcho
	 * @param usrId
	 * @return
	 * @return : List<RoleUsrMapgEntity>
	 * @description : 사용자기준 역할-사용자 매핑 정보 조회
	 */
	List<RoleUsrMapgEntity> getRoleUsrMapngByUsr(String usrId);

	/**
	 *
	 * @methodName : deleteRoleUsrMapgList
	 * @author : cwcho
	 * @param roleUsrMapgList
	 * @return
	 * @return : Integer
	 * @description : 역할-사용자 매핑 목록 삭제
	 */
	Integer deleteRoleUsrMapgList(List<RoleUsrMapgEntity> roleUsrMapgList);

	/**
	 *
	 * @methodName : deleteRoleApiAuthMapgList
	 * @author : cwcho
	 * @param roleApiAuthMapgEntityList
	 * @return
	 * @return : Integer
	 * @description : 역할-API-권한 매핑 목록 삭제
	 */
	Integer deleteRoleApiAuthMapgList(List<RoleApiAuthMapgEntity> roleApiAuthMapgEntityList);

	/**
	 *
	 * @methodName : deleteRoleMenuScrnMapgList
	 * @author : cwcho
	 * @param roleMenuScrnMapgEntityList
	 * @return
	 * @return : Integer
	 * @description : 역할-메뉴-화면 매핑 목록 삭제
	 */
	Integer deleteRoleMenuScrnMapgList(List<RoleMenuScrnMapgEntity> roleMenuScrnMapgEntityList);

	/**
	 *
	 * @methodName : updateRoleMenuScrnMapg
	 * @author : cwcho
	 * @param roleMenuScrnMapgEntity
	 * @return
	 * @return : Integer
	 * @description : 역할-메뉴-화면 매핑 수정
	 */
	Integer updateRoleMenuScrnMapg(RoleMenuScrnMapgEntity roleMenuScrnMapgEntity);

	/**
	 *
	 * @methodName : selectMenuApiMapg
	 * @author : nimbas
	 * @param menuId
	 * @return
	 * @return : MenuApiMapgResDto
	 * @description : 메뉴 API 매핑 정보 조회
	 */
	List<MenuApiMapgResDto> selectMenuApiMapg(String menuId);

	/**
	 *
	 * @methodName : selectApiAuth
	 * @author : nimbas
	 * @param menuId
	 * @return
	 * @return : MenuApiMapgResDto
	 * @description : API 상세 조회
	 */
	ApiAuthResDto selectApiAuth(ApiAuthReqDto reqDto);

	/**
	 *
	 * @methodName : selectMenuApiMapg
	 * @author : nimbas
	 * @param menuId
	 * @return
	 * @return : MenuApiMapgResDto
	 * @description : 메뉴 API 매핑 정보 조회
	 */
	List<MenuApiMapgResDto> selectCommonMenuApiMapg();
}