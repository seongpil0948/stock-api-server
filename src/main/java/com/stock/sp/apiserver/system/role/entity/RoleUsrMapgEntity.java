package com.stock.sp.apiserver.system.role.entity;

/**
 * 
 * @programName : RoleUsrMapgEntity
 * @author : cwcho
 * @description : 역할-사용자 매핑 Entity
 */
public class RoleUsrMapgEntity {
	/**
	 * 역할 아이디
	 */
	private String roleId;

	/**
	 * 사용자 아이디
	 */
	private String userId;

	/**
	 * 생성자 아이디
	 */
	private String loginId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleUsrMapgEntity) {
			RoleUsrMapgEntity roleUsrMapgEntity = ((RoleUsrMapgEntity) obj);
			String compareString = roleUsrMapgEntity.getRoleId() + "|" + roleUsrMapgEntity.getUserId();
			String thisString = this.roleId + "|" + this.userId;
			return compareString.equals(thisString);
		}

		return false;
	}
}
