package kr.co.dsi.system.user.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 사용자
 */
@Getter
@Setter
@Schema(description = "사용자 정보")
public class UserReadResDto {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "열 번호", example = "1", required = true)
	private Integer rowNum;

	@Schema(description = "사용자 아이디", example = "actorlee", required = true, minLength = 1, maxLength = 50)
	private String userId;

	@Schema(description = "사용자 명", example = "홍길동", required = true, minLength = 1, maxLength = 100)
	private String userNm;

	@Schema(description = "활성화 여부", example = "ACTIVE")
	private String statCd;

	@Schema(description = "상태 코드 이름", example = "활성화")
	private String statCdNm;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "기본 본부", example = "서부")
	private String basBrch;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String basBrchNm;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Schema(description = "지번 도시 명")
	private String ltnoCityNm;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String ltnoCityCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "City 리스트")
	private List<UserLntoDto> cityList;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Schema(description = "지번 시군구 명")
	private String ltnoSggNm;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String ltnoSggCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "County 리스트")
	private List<UserLntoDto> countyList;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Schema(description = "지번 읍면동 명")
	private String ltnoEmdNm;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String ltnoEmdCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "District 리스트")
	private List<UserLntoDto> distictList;

	@Schema(description = "사번", example = "230919")
	private String employeeNum;

	@Schema(description = "역할 아이디", example = "ROLE_USER")
	private String roleId;

	@Schema(description = "역할 명", example = "일반 사용자")
	private String roleName;

//	@Schema(description = "사용 여부", example = "Y", required = true, minLength = 1, maxLength = 1)
//	private String useYn;

	@Schema(description = "등록자 ID", example = "admin", required = true, minLength = 1, maxLength = 100)
	private String createUserId;

	@Schema(description = "등록 날짜", example = "2023-06-27", required = true, minLength = 1, maxLength = 100)
	private String createDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "수정자 ID", example = "admin", required = true, minLength = 1, maxLength = 100)
	private String updateUserId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Schema(description = "수정 날짜", example = "2023-06-27", required = true, minLength = 1, maxLength = 100)
	private String updateDate;
}