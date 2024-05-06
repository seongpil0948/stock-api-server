package com.stock.sp.apiserver.common.login.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import java.util.List;

/**
 *
 * @programName : LginUsr
 * @author : cwcho
 * @description : 로그인 사용자 객체(세션에 저장)
 */
@ToString
public class LoginUserDto {
	/**
	 * 사용자 아이디
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userId;
	/**
	 * 사용자 명
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userName;

	/**
	 * 사번
	 */
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
	 * 사용자에 할당된 메뉴 및 화면 목록
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<LoginUserMenuDto> menuScreenInfo;

	/**
	 * 사용자에 할당된 역할 목록
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String role;

	@JsonIgnore
	private String roleName;

	/**
	 * 로그인 사용자 API 응답용 정보
	 */
	@JsonIgnore
	private LoginUserSimpleInfoDto loginUserSimpleInfoDto;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<LoginUserMenuDto> getMenuScreenInfo() {
		return menuScreenInfo;
	}

	public void setMenuScreenInfo(List<LoginUserMenuDto> menuScreenInfo) {
		this.menuScreenInfo = menuScreenInfo;
	}

	public void setLoginUserSimpleInfo(LoginUserSimpleInfoDto loginUserSimpleInfoDto) {
		this.loginUserSimpleInfoDto = loginUserSimpleInfoDto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LoginUserSimpleInfoDto getLoginUserSimpleInfoDto() {
		if (this.loginUserSimpleInfoDto == null) {
			loginUserSimpleInfoDto = new LoginUserSimpleInfoDto();
		}

		loginUserSimpleInfoDto.setUserId(this.userId);
		loginUserSimpleInfoDto.setUserName(this.userName);
		loginUserSimpleInfoDto.setEmployeeNum(this.employeeNum);
		loginUserSimpleInfoDto.setBaseHq(this.baseHq);
		loginUserSimpleInfoDto.setBaseHqCode(this.baseHqCode);
		loginUserSimpleInfoDto.setMenuScreenInfo(this.menuScreenInfo);
		loginUserSimpleInfoDto.setBaseCity(this.baseCity);
		loginUserSimpleInfoDto.setBaseCityCode(this.baseCityCode);
		loginUserSimpleInfoDto.setBaseCounty(this.baseCounty);
		loginUserSimpleInfoDto.setBaseCountyCode(this.baseCountyCode);
		loginUserSimpleInfoDto.setBaseDistrict(this.baseDistrict);
		loginUserSimpleInfoDto.setBaseDistrictCode(this.baseDistrictCode);
		loginUserSimpleInfoDto.setBaseDong(this.baseDong);
		return loginUserSimpleInfoDto;
	}

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getBaseHq() {
		return baseHq;
	}

	public void setBaseHq(String baseHq) {
		this.baseHq = baseHq;
	}

	public String getBaseCity() {
		return baseCity;
	}

	public void setBaseCity(String baseCity) {
		this.baseCity = baseCity;
	}

	public String getBaseCounty() {
		return baseCounty;
	}

	public void setBaseCounty(String baseCounty) {
		this.baseCounty = baseCounty;
	}

	public String getBaseDistrict() {
		return baseDistrict;
	}

	public void setBaseDistrict(String baseDistrict) {
		this.baseDistrict = baseDistrict;
	}

	public String getBaseDong() {
		return baseDong;
	}

	public void setBaseDong(String baseDong) {
		this.baseDong = baseDong;
	}

	public String getRoleName() { return roleName; }

	public void setRoleName(String roleName) { this.roleName = roleName; }

	public String getBaseHqCode() {
		return baseHqCode;
	}

	public void setBaseHqCode(String baseHqCode) {
		this.baseHqCode = baseHqCode;
	}

	public String getBaseCityCode() {
		return baseCityCode;
	}

	public void setBaseCityCode(String baseCityCode) {
		this.baseCityCode = baseCityCode;
	}

	public String getBaseCountyCode() {
		return baseCountyCode;
	}

	public void setBaseCountyCode(String baseCountyCode) {
		this.baseCountyCode = baseCountyCode;
	}

	public String getBaseDistrictCode() {
		return baseDistrictCode;
	}

	public void setBaseDistrictCode(String baseDistrictCode) {
		this.baseDistrictCode = baseDistrictCode;
	}
}
