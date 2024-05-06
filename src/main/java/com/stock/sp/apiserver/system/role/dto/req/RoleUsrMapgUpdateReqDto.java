package kr.co.dsi.system.role.dto.req;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @programName : RoleUsrMapgCreateReqDto
 * @author : cwcho
 * @description : 역할-사용자 매핑 정보 생성 Dto
 */
@Schema(description = "역할-사용자 매핑 정보 생성")
public class RoleUsrMapgUpdateReqDto {
	/**
	 * 사용자 아이디
	 */
	@Schema(description = "사용자 아이디")
	private List<String> userIdList;

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
}
