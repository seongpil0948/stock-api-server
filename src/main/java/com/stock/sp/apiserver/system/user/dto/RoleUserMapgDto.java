package kr.co.dsi.system.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUserMapgDto {
    @Schema(description = "사용자 ID", example = "actorlee", minLength = 1, maxLength = 50)
    private String userId;
    @Schema(description = "역할 ID", example = "ROLE_USER", minLength = 1, maxLength = 50)
    private String roleId;
    @JsonIgnore
    private String updUserId;
}
