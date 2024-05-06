package com.stock.sp.apiserver.system.role.service;

import com.stock.sp.apiserver.common.exception.BizException;
import com.stock.sp.apiserver.common.login.dto.res.LginUsrMenuDto;
import com.stock.sp.apiserver.spring.session.SessionAttributeManager;
import com.stock.sp.apiserver.system.role.dao.RoleDao;
import com.stock.sp.apiserver.system.role.dto.RoleMenuScrnMapg;
import com.stock.sp.apiserver.system.role.dto.RoleUsrDto;
import com.stock.sp.apiserver.system.role.dto.req.*;
import com.stock.sp.apiserver.system.role.dto.res.ApiAuthResDto;
import com.stock.sp.apiserver.system.role.dto.res.MenuApiMapgResDto;
import com.stock.sp.apiserver.system.role.dto.res.RoleMenuScrnMapgReadResDto;
import com.stock.sp.apiserver.system.role.dto.res.RoleReadResDto;
import com.stock.sp.apiserver.system.role.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @programName : LginSvc
 * @author : cwcho
 * @description : 로그인관련 기능
 */
@Service
public class RoleService {
	@Autowired
	RoleDao roleDao;

	// @Autowired
	// ApiDao apiDao;

	@Value("${spring.application.name}")
	private String appName;

	@Value("${id.id-pattern}")
	private String pattern;

	/**
	 *
	 * @methodName : login
	 * @author : cwcho
	 * @return : LginUsr
	 * @description : 로그인 요청 사용자 체크
	 */
	public List<RoleUsrDto> getLginUsrRole(String usrId) {
		return roleDao.getLginUsrRole(usrId, this.appName);
	}

	/**
	 *
	 * @methodName : getRoleMenuScrn
	 * @author : cwcho
	 * @return : List<MenuScrnResDto>
	 * @description : 역할에 할당된 메뉴-화면 정보 조회(관리자페이용)
	 */
	public List<LginUsrMenuDto> getRoleMenuScrn(List<RoleUsrDto> roleList, boolean checkUseYn) {
		// 역할별 메뉴 트리를 정리한다.
		List<RoleMenuScrnMapg> roleMenuScrnMapgList = null;
		if (roleList != null && !roleList.isEmpty()) {
			if (checkUseYn) {
				// 역할 및 메뉴의 사용여부를 체크하여 조회
				roleMenuScrnMapgList = roleDao.getRoleMenuScrnRoleUse(roleList);
			} else {
				roleMenuScrnMapgList = roleDao.getRoleMenuScrn(roleList);
			}
		}

		HashMap<String, LginUsrMenuDto> menuMap = new HashMap<String, LginUsrMenuDto>();

		// 메뉴 순서 정렬
		List<LginUsrMenuDto> lginUsrMenuDtoList = new ArrayList<LginUsrMenuDto>();

		// List to Map(메뉴ID기준) 정렬
		if (roleMenuScrnMapgList != null) {
			for (RoleMenuScrnMapg roleMenuScrnMapg : roleMenuScrnMapgList) {
				if (menuMap.get(roleMenuScrnMapg.getMenuId()) == null) {
					LginUsrMenuDto lginUsrMenuDto = new LginUsrMenuDto();
					lginUsrMenuDto.setMenuId(roleMenuScrnMapg.getMenuId());
					lginUsrMenuDto.setMenuIndex(roleMenuScrnMapg.getMenuIdx());
					lginUsrMenuDto.setMenuName(roleMenuScrnMapg.getMenuNm());
					lginUsrMenuDto.setParentMenuId(roleMenuScrnMapg.getHposMenuId());
					lginUsrMenuDto.setMenuOrder(roleMenuScrnMapg.getMenuOrder());
					lginUsrMenuDto.setMenuUrl(roleMenuScrnMapg.getMenuUrl());

					// 역할-메뉴-화면 으로 정확하게 할당된 화면만
					// if (roleMenuScrnMapg.getScrnNm() != null) {
					// LginUsrScrnDto lginRoleScrn = new LginUsrScrnDto();
					// lginRoleScrn.setScreenId(roleMenuScrnMapg.getScrnId());
					// lginRoleScrn.setMainScreenYn(roleMenuScrnMapg.getMainScrnYn());
					// lginRoleScrn.setScreenName(roleMenuScrnMapg.getScrnNm());
					// lginRoleScrn.setScreenPath(roleMenuScrnMapg.getScrnPath());
					//
					// lginUsrMenuDto.getScreenList().add(lginRoleScrn);
					// }

					menuMap.put(roleMenuScrnMapg.getMenuId(), lginUsrMenuDto);
					// } else {
					// LginUsrMenuDto lginUsrMenuDto = menuMap.get(roleMenuScrnMapg.getMenuId());

					// 역할-메뉴-화면 으로 정확하게 할당된 화면만
					// if (roleMenuScrnMapg.getScrnNm() != null) {
					// LginUsrScrnDto lginRoleScrn = new LginUsrScrnDto();
					// lginRoleScrn.setScreenId(roleMenuScrnMapg.getScrnId());
					// lginRoleScrn.setMainScreenYn(roleMenuScrnMapg.getMainScrnYn());
					// lginRoleScrn.setScreenName(roleMenuScrnMapg.getScrnNm());
					// lginRoleScrn.setScreenPath(roleMenuScrnMapg.getScrnPath());
					// lginUsrMenuDto.getScreenList().add(lginRoleScrn);
					// }
				}
			}

			// 1. Map to List
			lginUsrMenuDtoList = new ArrayList<>(menuMap.values());

			// 2. 메뉴 순서 정렬
			if (lginUsrMenuDtoList != null && !lginUsrMenuDtoList.isEmpty()) {
				Collections.sort(lginUsrMenuDtoList, new Comparator<LginUsrMenuDto>() {
					@Override
					public int compare(LginUsrMenuDto a1, LginUsrMenuDto a2) {
						if (a1.getMenuIndex().compareTo(a2.getMenuIndex()) >= 0) {
							return 1;
						} else if (a1.getMenuIndex().compareTo(a2.getMenuIndex()) < 0) {
							return -1;
						}

						return 0;
					}
				});
			}
		}
		return lginUsrMenuDtoList;
	}

	/**
	 *
	 * @methodName : selectRoleCount
	 * @author : cwcho
	 * @param roleReadReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할 목록 조회 카운트
	 */
	public Integer selectRoleCount(RoleReadReqDto roleReadReqDto) {
		return roleDao.selectRoleCount(roleReadReqDto);
	}

	/**
	 *
	 * @methodName : selectRoleList
	 * @author : cwcho
	 * @param roleReadReqDto
	 * @return
	 * @return : List<RoleReadResDto>
	 * @description : 역할 목록 조회
	 */
	public List<RoleReadResDto> selectRoleList(RoleReadReqDto roleReadReqDto) {
		return roleDao.selectRoleList(roleReadReqDto);
	}

	/**
	 *
	 * @methodName : selectRoleOne
	 * @author : cwcho
	 * @param roleReadReqDto
	 * @return
	 * @return : RoleReadResDto
	 * @description : 역할 단건 조회
	 */
	public RoleReadResDto selectRoleOne(RoleReadReqDto roleReadReqDto) {
		RoleReadResDto result = new RoleReadResDto();
		result = roleDao.selectRole(roleReadReqDto);

		if (result == null) {
			throw new BizException("data_not_found");
		} else {
			result.setMenuRoleList(roleDao.selectAssignMenuRoleList(roleReadReqDto));
			return result;
		}
	}

	/**
	 *
	 * @methodName : insertRole
	 * @author : cwcho
	 * @param roleCreateReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할 등록
	 */
	@Transactional
	public void insertRole(RoleCreateReqDto roleCreateReqDto) {
		if (!Pattern.matches(pattern, roleCreateReqDto.getRoleId())) {
			throw new BizException("invalid-id");
		}
		String roleId = roleCreateReqDto.getRoleId();
		// 기존에 등록된 역할아이디 기준으로 중복된게 없는지 먼저 조회한다.
		RoleReadReqDto roleReadReqDto = new RoleReadReqDto();
		roleReadReqDto.setRoleId(roleCreateReqDto.getRoleId());
		RoleReadResDto roleReadResDto = roleDao.selectRole(roleReadReqDto);
		// 중복된 역할아이디 발견
		if (roleReadResDto != null) {
			throw new BizException("duplicated_id", Arrays.asList("역할 ID가"));
		}

		// 로그인 사용자 정보
		String loginId = SessionAttributeManager.getLoginUserId();

		Role roleEnity = new Role();
		roleEnity.setRoleId(roleCreateReqDto.getRoleId());
		roleEnity.setUseYn(roleCreateReqDto.getUseYn());
		roleEnity.setRoleNm(roleCreateReqDto.getRoleName());
		roleEnity.setRoleDscr(roleCreateReqDto.getRoleDescription());
		roleEnity.setLoginId(loginId);

		// tb_role 테이블 등록 (역할 등록)
		if (roleDao.insertRole(roleEnity) < 1) {
			throw new BizException("fail_add");
		}

		// 메뉴 역할 권한 부여 tb_role_menu_mapg 테이블 등록(메뉴 역할 등록)
		if (!roleCreateReqDto.getMenuRoleList().isEmpty()) {
			List<RoleMenuScrnMapgEntity> insertRoleEntityList = new ArrayList<RoleMenuScrnMapgEntity>();

			// TB_ROLE_MENU_MAPG(역활-메뉴 맵핑) 테이블 등록
			for (MenuApiMapgResDto req : roleCreateReqDto.getMenuRoleList()) {
				RoleMenuScrnMapgEntity entity = new RoleMenuScrnMapgEntity();
				entity.setRoleId(roleId);
				entity.setMenuId(req.getMenuId());
				entity.setMainScrnYn(req.getAssignYn());
				entity.setLoginId(loginId);
				insertRoleEntityList.add(entity);
			}

			if (roleDao.insertRoleMenuScrnMapgList(insertRoleEntityList) < 1) {
				throw new BizException("fail_add");
			}
		}
	}

	/**
	 *
	 * @methodName : updateRole
	 * @author : cwcho
	 * @param roleUpdateReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할 수정
	 */
	@Transactional
	public void updateRole(String roleId, RoleUpdateReqDto roleUpdateReqDto) {
		// 로그인 사용자 정보
		String loginId = SessionAttributeManager.getLoginUserId();

		Role roleEnity = new Role();
		roleEnity.setRoleId(roleId);
		roleEnity.setRoleNm(roleUpdateReqDto.getRoleName());
		roleEnity.setRoleDscr(roleUpdateReqDto.getRoleDescription());
		roleEnity.setUseYn(roleUpdateReqDto.getUseYn());
		roleEnity.setLoginId(loginId);

		if (roleDao.updateRole(roleEnity) < 1) {
			throw new BizException("fail_update");
		}

		if (!roleUpdateReqDto.getMenuScrnMapg().isEmpty()) {
			List<RoleMenuScrnMapgEntity> updateMenuRoleList = new ArrayList<RoleMenuScrnMapgEntity>();

			// TB_ROLE_MENU_MAPG(역활-메뉴 맵핑) 테이블 등록
			for (MenuScrnMapgUpdateReqDto req : roleUpdateReqDto.getMenuScrnMapg()) {
				RoleMenuScrnMapgEntity entity = new RoleMenuScrnMapgEntity();
				entity.setRoleId(roleId);
				entity.setMenuId(req.getMenuId());
				entity.setMainScrnYn(req.getMainScreenYn());
				entity.setLoginId(loginId);
				updateMenuRoleList.add(entity);
			}
			roleDao.deleteRoleMenuScrnMapgByRoleId(roleId);

			if (roleDao.insertRoleMenuScrnMapgList(updateMenuRoleList) < 1) {
				throw new BizException("fail_add");
			}
		}
	}

	/**
	 *
	 * @methodName : deleteRole
	 * @author : cwcho
	 * @param List<String> roleId
	 * @return
	 * @return : void
	 * @description : 역할 삭제
	 */
	@Transactional
	public void deleteRole(String roleId) {
		String loginId = SessionAttributeManager.getLoginUserId();

		Role roleEnity = new Role();
		roleEnity.setRoleId(roleId);
		roleEnity.setLoginId(loginId);
		roleDao.deleteRole(roleEnity);
	}

	/**
	 *
	 * @methodName : updateRoleMenuScrnMapgAll
	 * @author : cwcho
	 * @param roleId
	 * @param roleMenuScrnMapgCreateReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할아이디 기준 모든 역할-메뉴-화면 매핑 정보를 갱신한다.(전체 삭제 후 등록)
	 */
	@Transactional
	public Integer updateRoleMenuScrnMapgAll(String roleId, RoleMenuScrnMapgUpdateReqDto roleMenuScrnMapgCreateReqDto) {
		// 로그인 사용자 정보
		String loginId = SessionAttributeManager.getLoginUserId();

		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		// vvvvvvvvvvvvvvvvvvvvv 삭제/추가 대상건 추출 vvvvvvvvvvvvvvvvvvvvv
		// insert 또는 delete 되는 대상의 이력을 남기기위한 작업
		List<RoleMenuScrnMapgEntity> removeTargetList = new ArrayList<RoleMenuScrnMapgEntity>(); // 삭제 대상
		List<RoleMenuScrnMapgEntity> noChangeTargetList = new ArrayList<RoleMenuScrnMapgEntity>(); // 유지 대상
		List<RoleMenuScrnMapgEntity> reqList = new ArrayList<RoleMenuScrnMapgEntity>(); // 요청한 대상
		List<RoleApiAuthMapgEntity> apiAuthList = new ArrayList<RoleApiAuthMapgEntity>(); // menu 등록시 API 권한도 같이 등록하기 위한 정보

		RoleMenuScrnMapgEntity roleMenuScrnMapgEntity = new RoleMenuScrnMapgEntity();
		roleMenuScrnMapgEntity.setRoleId(roleId);
		List<RoleMenuScrnMapgEntity> orinList = roleDao.getRoleMenuScrnMapgBySelective(roleMenuScrnMapgEntity); // 현재 메뉴 목록
																																																						// 조회

		// 요청목록 변환 -> Entity
		if (roleMenuScrnMapgCreateReqDto.getMenuScrnMapg() != null
				&& roleMenuScrnMapgCreateReqDto.getMenuScrnMapg().size() > 0) {
			for (MenuScrnMapgUpdateReqDto menuScrnMapgUpdateReqDto : roleMenuScrnMapgCreateReqDto.getMenuScrnMapg()) {
				RoleMenuScrnMapgEntity roleUsrMapgEntity = new RoleMenuScrnMapgEntity();
				roleUsrMapgEntity.setRoleId(roleId);
				roleUsrMapgEntity.setMenuId(menuScrnMapgUpdateReqDto.getMenuId());
				// roleUsrMapgEntity.setScrnId(menuScrnMapgUpdateReqDto.getScreenId());
				roleUsrMapgEntity.setMainScrnYn(menuScrnMapgUpdateReqDto.getMainScreenYn());
				roleUsrMapgEntity.setLoginId(loginId);
				reqList.add(roleUsrMapgEntity);
			}

			// 메뉴 관련 API 정보 등록
			for (MenuScrnMapgUpdateReqDto menuScrnMapgUpdateReqDto : roleMenuScrnMapgCreateReqDto.getMenuScrnMapg()) {
				List<MenuApiMapgResDto> menuApiMapgResList = roleDao.selectMenuApiMapg(menuScrnMapgUpdateReqDto.getMenuId());
				if (menuApiMapgResList != null && menuApiMapgResList.size() > 0) {
					// 1. 먼저 API AUTH 정보를 삭제 실행
					roleDao.deleteRoleApiAuthMapgByRoleId(roleId);
					// 2. 메뉴 별 API AUTH 정보 입력
					for (MenuApiMapgResDto apiMapgItem : menuApiMapgResList) {
						RoleApiAuthMapgEntity apiAuthMapgEntity = new RoleApiAuthMapgEntity();
						ApiAuthReqDto apiAuthReqDto = new ApiAuthReqDto();
						apiAuthReqDto.setApiId(apiMapgItem.getApiId());
						// apiAuthReqDto.setRoleType(roleMenuScrnMapgCreateReqDto.getRoleType());
						ApiAuthResDto apiAuthResDto = roleDao.selectApiAuth(apiAuthReqDto);
						apiAuthMapgEntity.setAuthId(apiAuthResDto.getAuthId());
						apiAuthMapgEntity.setApiId(apiAuthResDto.getApiId());
						apiAuthMapgEntity.setRoleId(roleId);
						apiAuthMapgEntity.setLoginId(loginId);
						apiAuthList.add(apiAuthMapgEntity);
					}
					roleDao.insertRoleApiAuthMapgList(apiAuthList);
				}
			}

			// UMVCS일 경우 공통 API 매핑 정보 들어가게 수정 해야된다.
			if ("UMVCS".equals(roleMenuScrnMapgCreateReqDto.getRoleType())) {
				List<RoleApiAuthMapgEntity> commonApiAuthList = new ArrayList<RoleApiAuthMapgEntity>();
				List<MenuApiMapgResDto> commonMenuApiMapgResList = roleDao.selectCommonMenuApiMapg();
				for (MenuApiMapgResDto commonMenuApiMapgItem : commonMenuApiMapgResList) {
					RoleApiAuthMapgEntity commonApiAuthMapgEntity = new RoleApiAuthMapgEntity();
					ApiAuthReqDto commonApiAuthReqDto = new ApiAuthReqDto();
					// commonApiAuthReqDto.setApiId(commonMenuApiMapgItem.getApiId());
					// commonApiAuthReqDto.setRoleType(roleMenuScrnMapgCreateReqDto.getRoleType());
					ApiAuthResDto commonApiAuthResDto = roleDao.selectApiAuth(commonApiAuthReqDto);
					commonApiAuthMapgEntity.setAuthId(commonApiAuthResDto.getAuthId());
					commonApiAuthMapgEntity.setApiId(commonApiAuthResDto.getApiId());
					commonApiAuthMapgEntity.setRoleId(roleId);
					commonApiAuthMapgEntity.setLoginId(loginId);
					commonApiAuthList.add(commonApiAuthMapgEntity);
				}
				roleDao.insertRoleApiAuthMapgList(commonApiAuthList);
			}

		} else {
			roleDao.deleteRoleApiAuthMapgByRoleId(roleId);
		}

		boolean equal = false;
		for (RoleMenuScrnMapgEntity orin : orinList) {
			equal = false;
			for (RoleMenuScrnMapgEntity req : reqList) {
				if (orin.equals(req)) { // 재정의한 equals
					equal = true;
					break;
				}
			}
			if (equal) {
				// 변동 없음
				noChangeTargetList.add(orin);
			} else {
				// 삭제 대상
				removeTargetList.add(orin);
			}
		}

		// ^^^^^^^^^^^^^^^^^^^^^ 삭제/추가 대상건 추출 ^^^^^^^^^^^^^^^^^^^^^
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		List<RoleMenuScrnMapgHistEntity> roleUsrMapgHistEntityList = new ArrayList<RoleMenuScrnMapgHistEntity>(); // 이력 대상

		int result = 0;

		// 2. 수정 대상 메뉴를 업데이트 한다.
		for (RoleMenuScrnMapgEntity req : reqList) {
			equal = false;
			for (RoleMenuScrnMapgEntity noChange : noChangeTargetList) {
				if (req.equals(noChange)) { // 재정의한 equals
					equal = true;
					break;
				}
			}
			// 요청 정보중에 nochange와 같다는건 업데이트 건이다
			if (equal) {
				// req.setUpdUsrId(crteUsrId);
				// result += roleDao.updateRoleMenuScrnMapg(req); // 요청 정보로 업데이트
				// RoleMenuScrnMapgHistEntity roleMenuScrnMapgEntityHist = new
				// RoleMenuScrnMapgHistEntity();
				// roleMenuScrnMapgEntityHist.setRoleId(req.getRoleId());
				// roleMenuScrnMapgEntityHist.setMenuId(req.getMenuId());
				// roleMenuScrnMapgEntityHist.setScrnId(req.getScrnId());
				// roleMenuScrnMapgEntityHist.setMainScrnYn(req.getMainScrnYn());
				// roleMenuScrnMapgEntityHist.setCrteUsrId(crteUsrId);
				// roleMenuScrnMapgEntityHist.setPrssDivs(ComConstant.CUD_U); // 수정
				// roleUsrMapgHistEntityList.add(roleMenuScrnMapgEntityHist);
			}
		}

		// 요청한 대상에서 현재 유지건을 제외함
		reqList.removeAll(noChangeTargetList); // 변동없음 내역을 제거하면, 신규저장될 대상

		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		// vvvvvvvvvvvvvvvvvvvvv 역할-사용자 매핑 이력 저장 시작 vvvvvvvvvvvvvvvvvvvvv
		// insert 또는 delete 되는 대상의 이력을 남기기위한 작업

		// 3. 삭제 대상 메뉴를 삭제한다.
		if (!removeTargetList.isEmpty()) {
			result = roleDao.deleteRoleMenuScrnMapgList(removeTargetList);
		}

		// 4. 신규 입력 대상 메뉴를 등록한다.
		if (!reqList.isEmpty()) {
			result = roleDao.insertRoleMenuScrnMapgList(reqList);
		}

		return result;
	}

	/**
	 *
	 * @methodName : deleteRole
	 * @author : cwcho
	 * @param roleId
	 * @param menuId
	 * @return
	 * @return : Integer
	 * @description :
	 */
	public Integer deleteRoleMenuScrnMapg(String roleId, String menuId) {
		RoleMenuScrnMapgEntity roleMenuScrnMapgEntity = new RoleMenuScrnMapgEntity();
		roleMenuScrnMapgEntity.setRoleId(roleId);
		roleMenuScrnMapgEntity.setMenuId(menuId);

		return roleDao.deleteRoleMenuScrnMapg(roleMenuScrnMapgEntity);
	}

	/**
	 *
	 * @methodName : selectRoleMenuScrnMapgCount
	 * @author : cwcho
	 * @param roleMenuScrnMapgReadReqDto
	 * @return
	 * @return : Integer
	 * @description : 역할-메뉴-화면 매핑 정보 카운트
	 */
	public Integer selectRoleMenuScrnMapgCount(RoleMenuScrnMapgReadReqDto roleMenuScrnMapgReadReqDto) {
		return roleDao.selectRoleMenuScrnMapgCount(roleMenuScrnMapgReadReqDto);
	}

	/**
	 *
	 * @methodName : selectRoleMenuScrnMapg
	 * @author : cwcho
	 * @param roleMenuScrnMapgReadReqDto
	 * @return
	 * @return : List<RoleMenuScrnMapgReadResDto>
	 * @description : 역할-메뉴-화면 매핑 정보를 조회한다.
	 */
	public List<RoleMenuScrnMapgReadResDto> selectRoleMenuScrnMapg(
			RoleMenuScrnMapgReadReqDto roleMenuScrnMapgReadReqDto) {
		return roleDao.selectRoleMenuScrnMapg(roleMenuScrnMapgReadReqDto);
	}

	/**
	 *
	 * @methodName : insertRoleUsrMapg
	 * @author : cwcho
	 * @param roleId
	 * @param roleUsrMapgUpdateReqDto
	 * @return
	 * @return : Integer
	 * @description : 특정 역할기준 역할-사용자 매핑 정보 등록(멀티건)
	 */
	@Transactional
	public Integer updateRoleUsrMapg(String roleId, RoleUsrMapgUpdateReqDto roleUsrMapgUpdateReqDto) {
		// 로그인 사용자 정보
		String loginId = SessionAttributeManager.getLoginUserId();

		// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		// vvvvvvvvvvvvvvvvvvvvv 삭제/추가 대상건 추출 vvvvvvvvvvvvvvvvvvvvv
		// insert 또는 delete 되는 대상의 이력을 남기기위한 작업
		List<RoleUsrMapgEntity> removeTargetList = new ArrayList<RoleUsrMapgEntity>(); // 삭제 대상
		List<RoleUsrMapgEntity> noChangeTargetList = new ArrayList<RoleUsrMapgEntity>(); // 유지 대상
		List<RoleUsrMapgEntity> reqList = new ArrayList<RoleUsrMapgEntity>(); // 요청한 대상
		List<RoleUsrMapgEntity> orinList = roleDao.getRoleUsrMapngByRole(roleId); // 현재 메뉴 목록 조회

		// 요청목록 변환 -> Entity
		if (roleUsrMapgUpdateReqDto.getUserIdList() != null) {
			for (String userId : roleUsrMapgUpdateReqDto.getUserIdList()) {
				RoleUsrMapgEntity roleUsrMapgEntity = new RoleUsrMapgEntity();
				roleUsrMapgEntity.setRoleId(roleId);
				roleUsrMapgEntity.setUserId(userId);
				roleUsrMapgEntity.setLoginId(loginId);
				reqList.add(roleUsrMapgEntity);
			}
		}

		boolean equal = false;
		for (RoleUsrMapgEntity orin : orinList) {
			equal = false;
			for (RoleUsrMapgEntity req : reqList) {
				if (orin.equals(req)) { // 재정의한 equals
					equal = true;
					break;
				}
			}
			if (equal) {
				// 변동 없음
				noChangeTargetList.add(orin);
			} else {
				// 삭제 대상
				removeTargetList.add(orin);
			}
		}

		// 요청한 대상에서 현재 유지건을 제외함
		reqList.removeAll(noChangeTargetList); // 변동없음 내역을 제거하면, 신규저장될 대상

		// ^^^^^^^^^^^^^^^^^^^^^ 삭제/추가 대상건 추출 ^^^^^^^^^^^^^^^^^^^^^
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		int result = 0;

		// 2. 수정 대상 메뉴를 업데이트 한다.(매핑정보는 업데이트가 필요없음)

		// 3. 삭제 대상 메뉴를 삭제한다.
		if (!removeTargetList.isEmpty()) {
			result = roleDao.deleteRoleUsrMapgList(removeTargetList);
		}

		// 4. 신규 입력 대상 메뉴를 등록한다.
		if (!reqList.isEmpty()) {
			result = roleDao.insertRoleUsrMapgList(reqList);
		}

		return result;
	}

	// @Transactional(readOnly = true)
	// @CachePut(value = "roleApiAuthMapgForSpringSecurity", key =
	// "#applicationName")
	// public HashMap<ApiAuthPattern, HashSet<String>>
	// getRoleApiAuthMapgForReload(String applicationName) {
	// List<RoleApiAuthMapg> roleApiAuthMapgList =
	// roleDao.getRoleApiAuthMapgForSpringSecurity(applicationName);
	//
	// // AUTH_ID+API_PATTERN 기준으로 역할목록을 정리한다.
	// HashMap<ApiAuthPattern, HashSet<String>> arrangeApi = new
	// HashMap<ApiAuthPattern, HashSet<String>>();
	// for (RoleApiAuthMapg roleApiAuthMapg : roleApiAuthMapgList) {
	// ApiAuthPattern api = new ApiAuthPattern();
	// if (roleApiAuthMapg.getAuthId().endsWith(ComConstantAuth.RWD_R)) {
	// api.setAuthRwd(ComConstantAuth.RWD_R);
	// } else if (roleApiAuthMapg.getAuthId().endsWith(ComConstantAuth.RWD_W)) {
	// api.setAuthRwd(ComConstantAuth.RWD_W);
	// } else if (roleApiAuthMapg.getAuthId().endsWith(ComConstantAuth.RWD_D)) {
	// api.setAuthRwd(ComConstantAuth.RWD_D);
	// }
	// api.setApiPathPtrn(roleApiAuthMapg.getApiPathPtrn());
	//
	// if (arrangeApi.get(api) != null) {
	// arrangeApi.get(api).add(roleApiAuthMapg.getRoleId());
	// } else {
	// HashSet<String> roleSets = new HashSet<String>();
	// roleSets.add(roleApiAuthMapg.getRoleId());
	// arrangeApi.put(api, roleSets);
	// }
	// }
	//
	// return arrangeApi;
	// }

}
