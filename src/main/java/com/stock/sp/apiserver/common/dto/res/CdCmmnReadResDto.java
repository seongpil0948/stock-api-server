package com.stock.sp.apiserver.common.dto.res;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 코드
 */
@Schema(description = "코드 정보")
public class CdCmmnReadResDto {
	/**
	 * 코드
	 */
	@Schema(description = "API 아이디", example = "BBS", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String code;

	/**
	 * 코드 명
	 */
	@Schema(description = "코드 명", example = "게시판", required = true, minLength = 1, maxLength = 100)
	@NotEmpty
	@Size(min = 1, max = 100)
	private String codeName;

	/**
	 * 코드 값
	 */
	@Schema(description = "코드 값", example = "BBS", required = true, minLength = 1, maxLength = 20)
	@NotEmpty
	@Size(min = 1, max = 20)
	private String codeValue;

	/**
	 * 그룹 코드
	 */
	@NotEmpty
	@Size(min = 1, max = 50)
	@Schema(description = "그룹 코드", example = "APNDFILE_KD", required = true, minLength = 1, maxLength = 50)

	private String codeGroup;

	/**
	 * 코드 설명
	 */
	@Schema(description = "코드 설명", example = "게시판", required = true, minLength = 1, maxLength = 500)
	@NotEmpty
	@Size(min = 1, max = 500)
	private String codeDescription;

	/**
	 * 코드 순서
	 */
	@Schema(description = "코드 순서", example = "1", required = true, minLength = 1, maxLength = 50)
	@NotNull
	private Integer codeIndex;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public Integer getCodeIndex() {
		return codeIndex;
	}

	public void setCodeIndex(Integer codeIndex) {
		this.codeIndex = codeIndex;
	}

}