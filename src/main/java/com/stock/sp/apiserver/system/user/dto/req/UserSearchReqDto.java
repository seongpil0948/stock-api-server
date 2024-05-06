package kr.co.dsi.system.user.dto.req;

import kr.co.dsi.common.dto.web.ComPageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchReqDto extends ComPageDto {
    private String searchType;
    private String keyword;
    private String statCd;
}
