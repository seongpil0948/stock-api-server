package kr.co.dsi.system.role.entity;

/**
 * 
 * @programName : Role
 * @author : cwcho
 * @description : 역할 Entity
 */
public class Role {
	/**
	 * 역할 아이디
	 */
	private String roleId;

	/**
	 * 사용 여부
	 */
	private String useYn;

	/**
	 * 역할 명
	 */
	private String roleNm;

	/**
	 * 역할 설명
	 */
	private String roleDscr;

	private String loginId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	public String getRoleDscr() {
		return roleDscr;
	}

	public void setRoleDscr(String roleDscr) {
		this.roleDscr = roleDscr;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}