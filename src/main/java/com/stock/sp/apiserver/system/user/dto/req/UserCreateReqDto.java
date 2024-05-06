package kr.co.dsi.system.user.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @programName : UsrCreateReqDto
 * @author : cwcho
 * @description : 사용자 등록 Dto
 */
@Getter
@Setter
@Schema(description = "사용자 정보")
public class UserCreateReqDto {

	@Schema(description = "사용자 아이디(사번)", example = "actorlee", required = true, minLength = 1, maxLength = 50)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String userId;

	@Schema(description = "사용자 명", example = "홍길동", required = true, minLength = 1, maxLength = 100)
	@NotEmpty
	@Size(min = 1, max = 100)
	private String userNm;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?#&])[A-Za-z\\d@$!%*?&#]{8,}$",
			message = "최소 8자, 1개 이상의 대문자, 소문자, 숫자, 특수문자를 포함해야합니다.")
	@Schema(description = "비밀번호", example = "1q2w3e4er4r!", required = true)
	@NotEmpty
	private String pswd;

	@Schema(description = "권한 ID", example = "ROLE_USER", required = true)
	@NotEmpty
	private String roleId;

	@Schema(description = "기본 본부", maxLength = 50)
	private String basBrch;

	@Schema(description = "지번 도시 명", maxLength = 50)
	private String ltnoCityNm;

	@Schema(description = "지번 시군구 명", maxLength = 50)
	private String ltnoSggNm;

	@Schema(description = "지번 읍면동 명", maxLength = 50)
	private String ltnoEmdNm;

//	@Schema(description = "사번", example = "230919")
//	@NotEmpty
//	@Size(min = 1, max = 50)
//	private String employeeNum;

	@Schema(description = "활성화 여부", example = "ACTIVE", required = true, minLength = 1, maxLength = 1)
	@NotEmpty
	private String statCd;

	@JsonIgnore
	private String regUserId;
}