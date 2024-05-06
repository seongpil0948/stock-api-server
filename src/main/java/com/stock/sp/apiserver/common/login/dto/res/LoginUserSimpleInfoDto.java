package kr.co.dsi.common.login.dto.res;

import lombok.Data;

import java.util.List;

@Data
public class LoginUserSimpleInfoDto {
	// 사용자 아이디
	private String userId;
	// 사용자 명
    private String userName;
	private String employeeNum;
	// 기본 본부
	private String baseHq;
	private String baseHqCode;
	// 기본 도시
	private String baseCity;
	private String baseCityCode;
	// 기본 시군구
	private String baseCounty;
	private String baseCountyCode;
	// 기본 읍면동
	private String baseDistrict;
	private String baseDistrictCode;

	private String baseDong;
	/**
	 * 메뉴 정보
	 */
	private List<LoginUserMenuDto> menuScreenInfo;

}
