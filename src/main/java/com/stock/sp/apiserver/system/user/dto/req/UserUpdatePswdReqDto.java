package kr.co.dsi.system.user.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 *
 * @programName : UsrUpdatePswdReqDto
 * @author : cwcho
 * @description : 사용자 정보 수정 Dto
 */
@Getter
@Setter
@Schema(description = "사용자 정보 수정")
public class UserUpdatePswdReqDto {

	@JsonIgnore
	private String userId;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?#&])[A-Za-z\\d@$!%*?&#]{8,}$",
			message = "최소 8자, 1개 이상의 대문자, 소문자, 숫자, 특수문자를 포함해야합니다.")
	@Schema(description = "비밀번호", example = "1q2w3e4er4r", required = true)
	private String pswd;

	@Schema(description = "비밀번호 확인", example = "1q2w3e4er4r", required = true)
	private String pswdConfirm;

	@JsonIgnore
	private String updUserId;
}