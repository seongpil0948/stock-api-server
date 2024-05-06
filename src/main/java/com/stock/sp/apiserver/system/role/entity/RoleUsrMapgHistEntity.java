package com.stock.sp.apiserver.system.role.entity;

/**
 * 
 * @programName : RoleUsrMapgEntity
 * @author : cwcho
 * @description : 역할-사용자 매핑 이력 Entity
 */
public class RoleUsrMapgHistEntity extends RoleUsrMapgEntity {
	/**
	 * 처리 구분(C,U,D)
	 */
	private String prssDivs;

	public String getPrssDivs() {
		return prssDivs;
	}

	public void setPrssDivs(String prssDivs) {
		this.prssDivs = prssDivs;
	}
}
