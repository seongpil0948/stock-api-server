package kr.co.dsi.system.user.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUserReadResDto {

    @Schema(description = "열 번호")
    private Integer rowNum;
    
    @Schema(description = "사용자 아이디")
    private String userId;

    @Schema(description = "사용자 명")
    private String userNm;

    @Schema(description = "역할 명")
    private String roleNm;
}
