package kr.co.dsi.system.user.service;

import kr.co.dsi.common.dao.CommonDao;
import kr.co.dsi.common.exception.BizException;
import kr.co.dsi.common.utils.StringUtils;
import kr.co.dsi.spring.session.SessionAttributeManager;
import kr.co.dsi.system.user.dto.RoleUserMapgDto;
import kr.co.dsi.system.user.dto.req.*;
import kr.co.dsi.system.user.dto.res.RoleUserReadListResDto;
import kr.co.dsi.system.user.dto.res.UserReadListResDto;
import kr.co.dsi.system.user.dto.res.UserReadResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @programName : UserService
 * @author : thdtkddjs
 * @description : 사용자 관리
 */
@Slf4j
@Service
public class UserService {
	private final String USER_NAME_SPACE = "kr.co.dsi.system.user.";
	private final String ROLE_NAME_SPACE = "kr.co.dsi.system.role.dao.RoleDao.";
	private final String CODE_NAME_SPACE = "kr.co.dsi.system.cd.dao.CdDao.";

	@Autowired
	CommonDao commonDao;

	/**
	 * 비밀번호 암호화 모듈
	 */
	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${spring.security.login.secretKey}")
	private String aes256SecretKey;

	@Value("${pw.password-pattern}")
	private String passwordPattern;

	@Value("${id.id-pattern}")
	private String pattern;

	/**
	 * 
	 * @methodName : selectUsrList
	 * @author : cwcho
	 * @return : List<UserReadResDto>
	 * @description : 사용자 목록 조회(관리자 용도)
	 */
	public UserReadListResDto selectUserList(UserSearchReqDto userSearchReqDto) {
		UserReadListResDto result = new UserReadListResDto();

		result.setData(commonDao.selectList(USER_NAME_SPACE.concat("selectUserList"), userSearchReqDto));
		result.setTotalCount(commonDao.select(USER_NAME_SPACE.concat("selectUserTotalCount"), userSearchReqDto));

		return result;
	}
	/**
	 * 
	 * @methodName : selectUsrOne
	 * @author : cwcho
	 * @return : UserReadResDto
	 * @description : 단일 사용자 조회(관리자 용도)
	 */
	public UserReadResDto selectUserOne(String userId) {
		UserReadResDto userReadResDto = commonDao.select(USER_NAME_SPACE.concat("selectUser"), userId);

		if (StringUtils.isNotEmpty(userReadResDto.getBasBrch())) {
			userReadResDto.setCityList(commonDao.selectList(CODE_NAME_SPACE.concat("selectLtnoList"),
					userReadResDto.getBasBrch()));
			if (StringUtils.isNotEmpty(userReadResDto.getLtnoCityNm())) {
				userReadResDto.setCountyList(commonDao.selectList(CODE_NAME_SPACE.concat("selectLtnoList"),
						userReadResDto.getLtnoCityNm()));
				if (null != userReadResDto.getLtnoSggNm()) {
					userReadResDto.setDistictList(commonDao.selectList(CODE_NAME_SPACE.concat("selectLtnoList"),
							userReadResDto.getLtnoSggNm()));
				}
			}
		}

		return userReadResDto;
	}

	/**
	 * 
	 * @methodName : insertUsr
	 * @author : cwcho
	 * @return : Integer
	 * @description : 사용자 추가
	 */
	@Transactional
	public Integer insertUser(UserCreateReqDto userCreateReqDto) {

		String userPswd = userCreateReqDto.getPswd();

		String encryptPswd = null;
		if(!StringUtils.isEmpty(userPswd)) {
			if (!Pattern.matches(passwordPattern, userPswd)) {
				throw new BizException("invalid-password-pattern");
			}
		}
		encryptPswd = passwordEncoder.encode(userPswd);
		userCreateReqDto.setPswd(encryptPswd);

		String userId = commonDao.select(USER_NAME_SPACE.concat("checkDuplicateUser"), userCreateReqDto.getUserId());
		if (!StringUtils.isEmpty(userId)) {
			throw new BizException("duplicated_usr");
		}

		Integer result = commonDao.insert(USER_NAME_SPACE.concat("insertUser"), userCreateReqDto);
		commonDao.insert(USER_NAME_SPACE.concat("insertRoleUser"), userCreateReqDto);

		return result;
	}

	/**
	 * 
	 * @methodName : updateUser
	 * @author : cwcho
	 * @param userUpdateReqDto
	 * @return
	 * @return : Integer
	 * @description : 사용자 수정
	 */
	@Transactional
	public Integer updateUser(UserUpdateReqDto userUpdateReqDto) {

		Integer result = commonDao.update(USER_NAME_SPACE.concat("updateUser"), userUpdateReqDto);

		//역할 수정이 단건으로만 처리된다면 해당 Dto 삭제 후 Mapper의 parameter 수정
		if (null != userUpdateReqDto.getRoleId()) {
			RoleUserMapgDto roleUserMapgDto = new RoleUserMapgDto();
			roleUserMapgDto.setUserId(userUpdateReqDto.getUserId());
			roleUserMapgDto.setUpdUserId(userUpdateReqDto.getUpdUserId());
			roleUserMapgDto.setRoleId(userUpdateReqDto.getRoleId());

			commonDao.update(USER_NAME_SPACE.concat("updateRoleUser"), roleUserMapgDto);
		}

		return result;
	}

	/**
	 *
	 * @methodName : updateUserPwd
	 * @author : cwcho
	 * @param userUpdatePwdReqDto
	 * @return
	 * @return : Integer
	 * @description : 사용자 비밀번호 수정
	 */
	@Transactional
	public Integer updateUserPswd(UserUpdatePswdReqDto userUpdatePwdReqDto) {

		String userPswd= userUpdatePwdReqDto.getPswd();
		String userPswdConfirm = userUpdatePwdReqDto.getPswdConfirm();
		String encryptPswd = null;
		if(!StringUtils.isEmpty(userPswd)) {
			if(!userPswd.equals(userPswdConfirm)){
				throw new BizException("pw_not_equivalent");
			}
			if (!Pattern.matches(passwordPattern, userPswd)) {
				throw new BizException("invalid-password-pattern");
			}
		}
		encryptPswd = passwordEncoder.encode(userPswd);
		userUpdatePwdReqDto.setPswd(encryptPswd);

		Integer result = commonDao.update(USER_NAME_SPACE.concat("updateUserPwd"), userUpdatePwdReqDto);

		return result;
	}

	/**
	 * 
	 * @methodName : deleteUser
	 * @author : cwcho
	 * @param userId
	 * @return
	 * @return : void
	 * @description : 사용자 정보 삭제
	 */
	@Transactional
	public void deleteUser(String userId) {

		String checkId = commonDao.select(USER_NAME_SPACE.concat("checkDuplicateUser"), userId);
		if(StringUtils.isEmpty(checkId)){
			throw new BizException("user_empty");
		}
		commonDao.delete(ROLE_NAME_SPACE.concat("deleteAllRoleAssigned"), userId);

		commonDao.delete(USER_NAME_SPACE.concat("deleteUser"), userId);
	}

	/**
	 *
	 * @methodName : selectRoleUserList
	 * @author : thdtkddjs
	 * @param roleUserSearchReqDto
	 * @return
	 * @return : RoleUserReadListResDto
	 * @description : 권한 관리 목록의 사용자 목록을 불러온다.
	 */
	@Transactional
	public RoleUserReadListResDto selectRoleUserList(RoleUserSearchReqDto roleUserSearchReqDto) {

		RoleUserReadListResDto result = new RoleUserReadListResDto();

		result.setData(commonDao.selectList(USER_NAME_SPACE.concat("selectRoleUserList"), roleUserSearchReqDto));

		Integer totalCount =
				commonDao.select(USER_NAME_SPACE.concat("selectRoleUserTotalCount"), roleUserSearchReqDto);

		result.setTotalCount(totalCount);
		result.setTotalPage(totalCount/roleUserSearchReqDto.getLimit());
		result.setCurrentPage(roleUserSearchReqDto.getCurrentPage());
		result.setLimit(roleUserSearchReqDto.getLimit());

		return result;
	}
	/**
	 *
	 * @methodName : updateRoleUser
	 * @author : thdtkddjs
	 * @param data
	 * @return
	 * @return : Integer
	 * @description : 권한 관리 목록에서 변경한 사용자 별 권한을 업데이트한다. > 본 프로젝트에서 역할 수정 단건으로 처리 시 삭제
	 */
	@Transactional
	public void updateRoleUser(List<RoleUserMapgDto> data) {

		for (RoleUserMapgDto role : data){
			role.setUpdUserId(SessionAttributeManager.getLoginUserId());
			commonDao.update(USER_NAME_SPACE.concat("updateRoleUser"), role);
		}
	}
}
