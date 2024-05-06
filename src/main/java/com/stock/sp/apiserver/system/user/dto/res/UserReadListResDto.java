package com.stock.sp.apiserver.system.user.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import com.stock.sp.apiserver.common.dto.web.ComPagingResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "사용자에게 할당할 역할 아이디 목록")
public class UserReadListResDto extends ComPagingResponseDto {

	@Schema(description = "사용자에게 할당할 역할 아이디 목록")
	private List<UserReadResDto> data;
}
