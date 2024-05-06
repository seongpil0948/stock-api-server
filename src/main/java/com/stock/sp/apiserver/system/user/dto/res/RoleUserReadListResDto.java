package com.stock.sp.apiserver.system.user.dto.res;

import com.stock.sp.apiserver.common.dto.web.ComPagingResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleUserReadListResDto extends ComPagingResponseDto {
    private List<RoleUserReadResDto> data;
}
