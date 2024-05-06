package com.stock.sp.apiserver.system.role.dto.res;

import java.util.List;

import com.stock.sp.apiserver.common.dto.web.ComPagingResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "역할 정보")
public class RoleReadListResDto extends ComPagingResponseDto {

	@Schema(description = "역할 정보")
	private List<RoleReadResDto> data;
	private Integer totalCount;

	public List<RoleReadResDto> getData() {
		return data;
	}

	public void setData(List<RoleReadResDto> data) {
		this.data = data;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
