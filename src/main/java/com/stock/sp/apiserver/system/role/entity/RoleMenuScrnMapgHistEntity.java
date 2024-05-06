package kr.co.dsi.system.role.entity;

/**
 * 
 * @programName : RoleMenuScrnMapgHistEntity
 * @author : cwcho
 * @description : 역할-메뉴-화면 매핑 이력 정보 Entity
 */
public class RoleMenuScrnMapgHistEntity extends RoleMenuScrnMapgEntity {
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
