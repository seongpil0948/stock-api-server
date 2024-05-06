package com.stock.sp.apiserver.system.user.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @programName : UsrUpdateReqDto
 * @author : cwcho
 * @description : 사용자 정보 수정 Dto
 */
@Getter
@Setter
@Schema(description = "사용자 정보 수정")
public class UserUpdateReqDto {

	@JsonIgnore
	private String userId;

	@Schema(description = "사용자 명", example = "홍길동", required = true, minLength = 1, maxLength = 100)
	@Pattern(regexp = "^(?!\\s+$).+")
	@Size(min = 1, max = 100)
	private String userNm;

	@JsonIgnore
	private String updUserId;

	@Schema(description = "활성화 여부", example = "ACTIVE", required = true, minLength = 1, maxLength = 1)
	private String statCd;

	@Schema(description = "기본 본부", maxLength = 50)
	private String basBrch;

	@Schema(description = "지번 도시 명", maxLength = 50)
	private String ltnoCityNm;

	@Schema(description = "지번 시군구 명", maxLength = 50)
	private String ltnoSggNm;

	@Schema(description = "지번 읍면동 명", maxLength = 50)
	private String ltnoEmdNm;

	@Schema(description = "역할 아이디")
	private String roleId;
}