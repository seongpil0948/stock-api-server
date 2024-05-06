package com.stock.sp.apiserver.system.role.dto.req;

import com.stock.sp.apiserver.common.dto.web.ComPageDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 역할
 */
@Schema(description = "역할")
public class RoleReadReqDto extends ComPageDto {
	private String roleId;
	/**
	 * 검색 조건
	 */
	@Schema(description = "검색 조건", example = "ROLE_ID", required = false, minLength = 1, maxLength = 50)
	private String searchType;

	/**
	 * 검색어
	 */
	@Schema(description = "검색어", example = "시스템관리자", required = false, minLength = 1, maxLength = 100)
	private String keyword;

	/**
	 * 사용 여부
	 */
	@Schema(description = "사용 여부", example = "Y", required = false, minLength = 1, maxLength = 100)
	private String useYn;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}