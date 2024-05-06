package com.stock.sp.apiserver.system.role.dto.res;

import com.stock.sp.apiserver.common.dto.web.ComPagingResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "역할-api 매핑 정보 조회")
public class RoleApiReadListResDto extends ComPagingResponseDto {

	@Schema(description = "역할-api 매핑 정보 조회")
	private List<RoleApiMapgReadResDto> data;

	public List<RoleApiMapgReadResDto> getData() {
		return data;
	}

	public void setData(List<RoleApiMapgReadResDto> data) {
		this.data = data;
	}
}
