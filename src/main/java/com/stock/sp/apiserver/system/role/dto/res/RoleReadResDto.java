package kr.co.dsi.system.role.dto.res;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 역할
 */
@Schema(description = "역할 정보")
public class RoleReadResDto {
	/**
	 * 순번
	 */
	@Schema(description = "순번", example = "1", required = true, minLength = 1, maxLength = 50)
	private Integer no;

	/**
	 * 역할 아이디
	 */
	@Schema(description = "역할 아이디", example = "ROLE_ADMIN", required = true, minLength = 1, maxLength = 50)
	private String roleId;

	/**
	 * 역할 명
	 */
	@Schema(description = "역할 명", example = "시스템관리자", required = true, minLength = 1, maxLength = 100)
	private String roleName;

	/**
	 * 역할 설명
	 */
	@Schema(description = "역할 명", example = "시스템관리자", required = true, minLength = 1, maxLength = 100)
	private String roleDescription;

	/**
	 * 도메인 유형 코드
	 */
	@Schema(description = "도메인 유형 코드", example = "MGMT", required = true, minLength = 1, maxLength = 50)
	private String roleType;

	/**
	 * 사용 여부
	 */
	@Schema(description = "사용 여부", example = "Y", required = true, minLength = 1, maxLength = 1)
	private String useYn;

	/**
	 * 생성 일자
	 */
	@Schema(description = "생성 일자", example = "2020-11-24", required = true, minLength = 1, maxLength = 50)
	private String createDate;

	/**
	 * 등록 정보
	 */
	@Schema(description = "등록 정보", example = "admin / 2023-07-31", required = true, minLength = 1, maxLength = 50)
	private String createInfo;

	/**
	 * 수정 정보
	 */
	@Schema(description = "수정 정보", example = "admin / 2023-07-31", required = true, minLength = 1, maxLength = 50)
	private String updateInfo;


	/**
	 * 역할 할당 여부
	 */
	@Schema(description = "역할 할당 여부", example = "", required = true, minLength = 1, maxLength = 1)
	private boolean assigned;

	private List<MenuApiMapgResDto> menuRoleList;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateInfo() {
		return createInfo;
	}

	public void setCreateInfo(String createInfo) {
		this.createInfo = createInfo;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public List<MenuApiMapgResDto> getMenuRoleList() {
		return menuRoleList;
	}

	public void setMenuRoleList(List<MenuApiMapgResDto> menuRoleList) {
		this.menuRoleList = menuRoleList;
	}
}