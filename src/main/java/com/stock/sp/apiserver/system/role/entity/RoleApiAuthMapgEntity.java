package com.stock.sp.apiserver.system.role.entity;

/**
 * 
 * @programName : RoleApiAuthMapgEntity
 * @author : cwcho
 * @description : 역할-api-권한 매핑 정보 Entity
 */
public class RoleApiAuthMapgEntity {
	/**
	 * 권한 아이디
	 */
	private String authId;

	/**
	 * API 아이디
	 */
	private String apiId;

	/**
	 * 역할 아이디
	 */
	private String roleId;
	private String loginId;

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleApiAuthMapgEntity) {
			RoleApiAuthMapgEntity roleApiAuthMapgEntity = ((RoleApiAuthMapgEntity) obj);
			String compareString = roleApiAuthMapgEntity.getRoleId() + "|" + roleApiAuthMapgEntity.getApiId() + "|"
					+ roleApiAuthMapgEntity.getAuthId();
			String thisString = this.roleId + "|" + this.apiId + "|" + this.authId;
			return compareString.equals(thisString);
		}
		return false;
	}
}
