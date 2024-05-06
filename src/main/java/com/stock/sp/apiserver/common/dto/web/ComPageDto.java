package com.stock.sp.apiserver.common.dto.web;

import com.stock.sp.apiserver.common.constant.ComConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ===========================================
 *
 * @ProgramName : ComPageDto
 * @Author : LaVega
 * @Version : 1.0
 * @Description : Common Paging DTO Object
 *              ===========================================
 */
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class ComPageDto {
	@JsonIgnore
	private Integer currentPage;
	@JsonIgnore
	private Integer limit;

	public ComPageDto() {
		this.currentPage = ComConstant.DEFAULT_PAGE;
		this.limit = ComConstant.DEFAULT_LIMIT;
	}

	public Integer getCurrentPage() {
		if (this.currentPage == null) {
			this.currentPage = ComConstant.DEFAULT_PAGE;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getLimit() {
		if (this.limit == null) {
			this.limit = ComConstant.DEFAULT_LIMIT;
		}
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@JsonIgnore
	public Integer getRowStart() {
		if (this.currentPage == null) {
			this.currentPage = ComConstant.DEFAULT_PAGE;
		}
		if (this.limit == null) {
			this.limit = ComConstant.DEFAULT_LIMIT;
		}
		return (this.currentPage - 1) * limit;
	}
}