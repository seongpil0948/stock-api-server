package kr.co.dsi.system.role.dto.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @programName : RoleMenuScrnMapgReadReqDto
 * @author : cwcho
 * @description : 역할-메뉴-화면 매핑 정보 조회 Dto
 */
@Schema(description = "역할-메뉴-화면 매핑 정보 조회")
public class RoleMenuScrnMapgReadReqDto {

	/**
	 * 역할 아이디
	 */
	@Schema(description = "역할 아이디", example = "ROLE_ADMIN", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String roleId;

	/**
	 * 메뉴 아이디
	 */
	@Schema(description = "메뉴 아이디", example = "MGMT_CODE", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String menuId;

	/**
	 * 화면 아이디
	 */
	@Schema(description = "화면 아이디", example = "MGMT_CODE_GROUPUP_REG", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String screenId;

	/**
	 * 메인 화면 여부
	 */
	@Schema(description = "메인 화면 여부", example = "Y", required = true, minLength = 1, maxLength = 1)
	@NotEmpty
	@Size(min = 1, max = 1)
	private String mainScreenYn;

	public String getRoleId() {
		return roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public String getScreenId() {
		return screenId;
	}

	public String getMainScreenYn() {
		return mainScreenYn;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public void setMainScreenYn(String mainScreenYn) {
		this.mainScreenYn = mainScreenYn;
	}

}
