package kr.co.dsi.system.role.dto.res;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class RoleApiAuthMapgReadResDto {

	/**
	 * 역할 아이디
	 */
	@Schema(description = "역할 아이디", example = "ROLE_ADMIN", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String roleId;

	/**
	 * api 아이디
	 */
	@Schema(description = "api 아이디", example = "MGMT_AUTH_DELETE", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String apiId;

	/**
	 * api 아이디
	 */
	@Schema(description = "api 명", example = "권한 삭제 요청", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String apiNm;

	/**
	 * api 명
	 */
	@Schema(description = "권한 명", example = "모두 삭제", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String authNm;

	/**
	 * 권한 아이디
	 */
	@Schema(description = "권한 아이디", example = "ALL_D", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String authId;

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

	public String getApiNm() {
		return apiNm;
	}

	public void setApiNm(String apiNm) {
		this.apiNm = apiNm;
	}

	public String getAuthNm() {
		return authNm;
	}

	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

}
