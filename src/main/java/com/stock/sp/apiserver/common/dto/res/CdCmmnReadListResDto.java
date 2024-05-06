package kr.co.dsi.common.dto.res;

import java.util.List;

import kr.co.dsi.common.dto.web.ComPagingResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "코드 목록")
public class CdCmmnReadListResDto extends ComPagingResponseDto {

	@Schema(description = "코드 목록", example = "", required = true, minLength = 1, maxLength = 50)
	private List<CdCmmnReadResDto> data;

	public List<CdCmmnReadResDto> getData() {
		return data;
	}

	public void setData(List<CdCmmnReadResDto> data) {
		this.data = data;
	}

}
