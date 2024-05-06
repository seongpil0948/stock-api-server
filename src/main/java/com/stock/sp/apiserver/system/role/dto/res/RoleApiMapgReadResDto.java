package kr.co.dsi.system.role.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RoleApiMapgReadResDto {

	/**
	 * 역할 아이디
	 */
	@Schema(description = "역할 아이디", example = "ROLE_ADMIN", required = false, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String roleId;

	/**
	 * api 아이디
	 */
	@Schema(description = "api 아이디", example = "MGMT_AUTH_DELETE", required = false, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String apiId;


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
}
