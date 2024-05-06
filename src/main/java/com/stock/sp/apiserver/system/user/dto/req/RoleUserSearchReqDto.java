package com.stock.sp.apiserver.system.user.dto.req;

import com.stock.sp.apiserver.common.dto.web.ComPageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUserSearchReqDto extends ComPageDto {
    private String searchType;
    private String keyword;
}
