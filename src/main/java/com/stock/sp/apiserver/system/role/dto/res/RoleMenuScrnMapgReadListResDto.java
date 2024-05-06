package com.stock.sp.apiserver.system.role.dto.res;

import java.util.List;

import com.stock.sp.apiserver.common.dto.web.ComPagingResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "역할-메뉴-화면 매핑 정보 조회")
public class RoleMenuScrnMapgReadListResDto extends ComPagingResponseDto {

	@Schema(description = "역할-메뉴-화면 매핑 정보 조회")
	private List<RoleMenuScrnMapgReadResDto> data;

	public List<RoleMenuScrnMapgReadResDto> getData() {
		return data;
	}

	public void setData(List<RoleMenuScrnMapgReadResDto> data) {
		this.data = data;
	}
}
