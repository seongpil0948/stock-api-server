package kr.co.dsi.system.role.dto.res;

import java.util.List;

import kr.co.dsi.common.dto.web.ComPagingResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "역할-api-권한 매핑 정보 조회")
public class RoleApiAuthReadListResDto extends ComPagingResponseDto {

	@Schema(description = "역할-api-권한 매핑 정보 조회")
	private List<RoleApiAuthMapgReadResDto> data;

	public List<RoleApiAuthMapgReadResDto> getData() {
		return data;
	}

	public void setData(List<RoleApiAuthMapgReadResDto> data) {
		this.data = data;
	}
}
