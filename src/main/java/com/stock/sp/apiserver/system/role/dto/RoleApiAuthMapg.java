package com.stock.sp.apiserver.system.role.dto;

/**
 * 역할 API 권한 매핑
 */
public class RoleApiAuthMapg {
	/**
	 * 역할 아이디
	 */
	private String roleId;

	/**
	 * 권한 아이디
	 */
	private String authId;

	/**
	 * API 아이디
	 */
	private String apiId;

	/**
	 * API 명
	 */
	private String apiNm;

	/**
	 * API Ant Patter
	 */
	private String apiPathPtrn;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId == null ? null : authId.trim();
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId == null ? null : apiId.trim();
	}

	public String getApiNm() {
		return apiNm;
	}

	public void setApiNm(String apiNm) {
		this.apiNm = apiNm;
	}

	public String getApiPathPtrn() {
		return apiPathPtrn;
	}

	public void setApiPathPtrn(String apiPathPtrn) {
		this.apiPathPtrn = apiPathPtrn;
	}

}