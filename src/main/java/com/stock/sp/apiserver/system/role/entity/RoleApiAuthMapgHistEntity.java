package kr.co.dsi.system.role.entity;

/**
 * 
 * @programName : RoleApiAuthMapgHistEntity
 * @author : cwcho
 * @description : 역할-api-권한 매핑 이력 정보 Entity
 */
public class RoleApiAuthMapgHistEntity extends RoleApiAuthMapgEntity {
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
