package kr.co.dsi.system.role.dto.req;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @programName : RoleUpdateReqDto
 * @author : cwcho
 * @description : 역할 수정 요청 Dto
 */
@Schema(description = "역할 수정 요청")
public class RoleUpdateReqDto {

	/**
	 * 역할 명
	 */
	@Schema(description = "역할 명", example = "시스템관리자", required = true, minLength = 1, maxLength = 100)
	@NotEmpty
	@Size(min = 1, max = 100)
	private String roleName;

	/**
	 * 역할 설명
	 */
	@Schema(description = "역할 설명", example = "시스템관리자", required = true, minLength = 1, maxLength = 500)
	@NotEmpty
	@Size(min = 1, max = 500)
	private String roleDescription;

	/**
	 * 사용 여부
	 */
	@Schema(description = "사용 여부", example = "Y", required = true, minLength = 1, maxLength = 1)
	@NotEmpty
	@Size(min = 1, max = 1)
	private String useYn;

	@Schema(description = "역할-메뉴-화면 매핑 정보 생성")
	private List<MenuScrnMapgUpdateReqDto> menuScrnMapg;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public List<MenuScrnMapgUpdateReqDto> getMenuScrnMapg() {
		return menuScrnMapg;
	}

	public void setMenuScrnMapg(List<MenuScrnMapgUpdateReqDto> menuScrnMapg) {
		this.menuScrnMapg = menuScrnMapg;
	}
}