package com.stock.sp.apiserver.system.role.entity;

/**
 * 
 * @programName : RoleMenuScrnMapg
 * @author : cwcho
 * @description : 역할-메뉴-화면 매핑 정보 Entity
 */
public class RoleMenuScrnMapgEntity {
	/**
	 * 역할 아이디
	 */
	private String roleId;

	/**
	 * 메뉴 아이디
	 */
	private String menuId;

	/**
	 * 화면 아이디
	 */
	private String scrnId;

	/**
	 * 메인 화면 여부
	 */
	private String mainScrnYn;

	private String loginId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getScrnId() {
		return scrnId;
	}

	public void setScrnId(String scrnId) {
		this.scrnId = scrnId;
	}

	public String getMainScrnYn() {
		return mainScrnYn;
	}

	public void setMainScrnYn(String mainScrnYn) {
		this.mainScrnYn = mainScrnYn;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleMenuScrnMapgEntity) {
			RoleMenuScrnMapgEntity roleMenuScrnMapgEntity = ((RoleMenuScrnMapgEntity) obj);
			String compareString = roleMenuScrnMapgEntity.getRoleId() + "|" + roleMenuScrnMapgEntity.getMenuId() + "|"
					+ roleMenuScrnMapgEntity.getScrnId();
			String thisString = this.roleId + "|" + this.menuId + "|" + this.scrnId;
			return compareString.equals(thisString);
		}

		return false;
	}
}
