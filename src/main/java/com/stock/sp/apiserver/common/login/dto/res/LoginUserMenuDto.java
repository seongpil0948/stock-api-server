package kr.co.dsi.common.login.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserMenuDto {
    private String menuId;
    private String menuName;
    private String parentMenuId;
    private Integer menuIndex;
    private String menuPath;
    private String menuUrl;
}
