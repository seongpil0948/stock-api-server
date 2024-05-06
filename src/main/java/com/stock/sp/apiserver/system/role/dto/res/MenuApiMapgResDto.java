package com.stock.sp.apiserver.system.role.dto.res;

public class MenuApiMapgResDto {
	private String menuId;
	private String menuNm;
	private String apiId;
	private String assignYn;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getAssignYn() {
		return assignYn;
	}

	public void setAssignYn(String assignYn) {
		this.assignYn = assignYn;
	}
}
