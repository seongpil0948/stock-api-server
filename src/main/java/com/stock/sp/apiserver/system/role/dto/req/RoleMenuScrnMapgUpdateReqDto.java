package kr.co.dsi.system.role.dto.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @programName : RoleMenuScrnMapgCreateReqDto
 * @author : cwcho
 * @description : 역할-메뉴-화면 매핑 정보 생성 Dto
 */
@Schema(description = "역할-메뉴-화면 매핑 정보 생성")
public class RoleMenuScrnMapgUpdateReqDto {

	@JsonIgnore
	private String roleType;

	@Schema(description = "역할-메뉴-화면 매핑 정보 생성")
	private List<MenuScrnMapgUpdateReqDto> menuScrnMapg;

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<MenuScrnMapgUpdateReqDto> getMenuScrnMapg() {
		return menuScrnMapg;
	}

	public void setMenuScrnMapg(List<MenuScrnMapgUpdateReqDto> menuScrnMapg) {
		this.menuScrnMapg = menuScrnMapg;
	}

}
