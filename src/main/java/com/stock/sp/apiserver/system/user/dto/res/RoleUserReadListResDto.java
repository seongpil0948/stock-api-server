package kr.co.dsi.system.user.dto.res;

import kr.co.dsi.common.dto.web.ComPagingResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleUserReadListResDto extends ComPagingResponseDto {
    private List<RoleUserReadResDto> data;
}
