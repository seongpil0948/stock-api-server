package com.stock.sp.apiserver.common.login.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LginUsrMenuDto {
	/**
	 * 메뉴 아이디
	 */
	private String menuId;

	/**
	 * 상쉬 메뉴 아이디
	 */
	private String parentMenuId;

	/**
	 * 메뉴 명
	 */
	private String menuName;

	/**
	 * 메뉴 순서(동일레벨)
	 */
	private Integer menuIndex;

	/**
	 * 메뉴 순서(전체메뉴에서)
	 */
	@JsonIgnore
	private String menuOrder;

	/**
	 * 대표 화면 경로
	 */
	private String menuUrl;

	/**
	 * 화면 목록
	 */
//	private List<LginUsrScrnDto> screenList = new ArrayList<LginUsrScrnDto>();

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public Integer getMenuIndex() {
		return menuIndex;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

}
