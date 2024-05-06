package kr.co.dsi.system.role.dto.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @programName : MenuScrnMapgReqDto
 * @author : cwcho
 * @description : 메뉴-화면 매핑 정보 생성 Dto
 */
@Schema(description = "메뉴-화면 매핑 정보 생성")
public class MenuScrnMapgUpdateReqDto {
	/**
	 * 메뉴 아이디
	 */
	@Schema(description = "메뉴 아이디", example = "ROLE_ADMIN", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String menuId;

	/**
	 * 화면 아이디
	 */
//	@Schema(description = "화면 아이디", example = "MGMT_CODE", required = true, minLength = 1, maxLength = 50)
//	@NotEmpty
//	@Size(min = 1, max = 50)
//	private String screenId;

	/**
	 * 메인 화면 여부
	 */
	@Schema(description = "화면 아이디", example = "Y", required = true, minLength = 1, maxLength = 1)
	@Size(min = 1, max = 1)
	private String mainScreenYn;

	public String getMenuId() {
		return menuId;
	}

//	public String getScreenId() {
//		return screenId;
//	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

//	public void setScreenId(String screenId) {
//		this.screenId = screenId;
//	}

	public String getMainScreenYn() {
		return mainScreenYn;
	}

	public void setMainScreenYn(String mainScreenYn) {
		this.mainScreenYn = mainScreenYn;
	}

}
