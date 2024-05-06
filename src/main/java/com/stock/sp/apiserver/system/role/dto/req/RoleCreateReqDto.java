package com.stock.sp.apiserver.system.role.dto.req;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.stock.sp.apiserver.system.role.dto.res.MenuApiMapgResDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @programName : RoleCreateReqDto
 * @author : cwcho
 * @description : 역할 생성 Dto
 */
@Schema(description = "역할 생성")
public class RoleCreateReqDto {
	/**
	 * 역할 아이디
	 */
	@Schema(description = "역할 아이디", example = "ROLE_ADMIN", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String roleId;

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
	 * 역할 타입 구분
	 */
	// @Schema(description = "역할 타입 구분(MGMT, UMVCS)", example = "MGMT", required =
	// true, minLength = 1, maxLength = 50)
	// @NotEmpty
	// @Size(min = 1, max = 50)
	// private String roleType;

	/**
	 * 사용 여부
	 */
	@Schema(description = "사용 여부", example = "Y", required = true, minLength = 1, maxLength = 1)
	@NotEmpty
	@Size(min = 1, max = 1)
	private String useYn;

	private List<MenuApiMapgResDto> menuRoleList;

	public String getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	// public String getRoleType() {
	// return roleType;
	// }
	//
	// public void setRoleType(String roleType) {
	// this.roleType = roleType;
	// }

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public List<MenuApiMapgResDto> getMenuRoleList() {
		return menuRoleList;
	}

	public void setMenuRoleList(List<MenuApiMapgResDto> menuRoleList) {
		this.menuRoleList = menuRoleList;
	}
}