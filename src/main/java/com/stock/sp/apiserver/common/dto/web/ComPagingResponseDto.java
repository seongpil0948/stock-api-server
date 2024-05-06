package com.stock.sp.apiserver.common.dto.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author : LaVega
 * @ProgramName : ComPagingResponseDto
 * @description : 공통 페이징 조회 결과 응답 Dto
 */
public class ComPagingResponseDto {
	@JsonInclude(Include.NON_NULL)
	private Integer totalPage;

	@JsonInclude(Include.NON_NULL)
	private Integer totalCount;

	@JsonInclude(Include.NON_NULL)
	private Integer currentPage;

	@JsonInclude(Include.NON_NULL)
	private Integer limit;

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
