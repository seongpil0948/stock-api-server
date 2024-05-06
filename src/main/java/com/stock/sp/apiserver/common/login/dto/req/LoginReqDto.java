package kr.co.dsi.common.login.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReqDto {
    private String userId;
    private String password;
    private String customerId;
    private String systemMasterYn;
    @JsonIgnore
    private String roleId;
}
