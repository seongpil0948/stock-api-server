package com.stock.sp.apiserver.spring.exception;

import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import com.stock.sp.apiserver.common.dto.web.ComResultDto;
import com.stock.sp.apiserver.common.exception.BizException;
import com.stock.sp.apiserver.common.exception.ExceptionInfoConfig;
import com.stock.sp.apiserver.common.utils.StringUtils;
import com.stock.sp.apiserver.spring.ComResponseEntity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.Map;

/**
 *
 * @programName : CommonExceptionHandler
 * @author : cwcho
 * @description :
 */
@ControllerAdvice
public class CommonExceptionHandler {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ExceptionInfoConfig exceptionInfoConfig;

	/**
	 *
	 * @methodName : handleMethodArgumentNotValidException
	 * @author : cwcho
	 * @param ex
	 * @return
	 * @throws Exception
	 * @return : ComResponseEntity<Void>
	 * @description : @Valid 어노테이션 관런 처리
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ComResponseEntity<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
			throws Exception {
		errorLogging(ex);
		BindingResult bindingResult = ex.getBindingResult();

		StringBuilder builder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			builder.append("[");
			builder.append(fieldError.getField());
			builder.append("](은)는 ");
			builder.append(fieldError.getDefaultMessage());
			builder.append(". 입력된 값: [");
			builder.append(fieldError.getRejectedValue());
			builder.append("]");
		}

		ComResultDto resultDto = getResultDto(ex.getClass().getName());
		int statusCode = Integer.valueOf(resultDto.getStatus());
		ComResponseDto<Void> result = new ComResponseDto<Void>();
		result.setHttpStatusCode(statusCode);
		result.setCode(resultDto.getCode());
		result.setMessage(resultDto.getMessage());
		result.setDetailMessage(builder.toString());
		return new ComResponseEntity<Void>(result, HttpStatus.valueOf(statusCode));
	}

	/**
	 *
	 * @methodName : handleBizException
	 * @author : cwcho
	 * @param ex
	 * @return
	 * @throws Exception
	 * @return : ComResponseEntity<Void>
	 * @description : 어플리케이션에서 정의하는 비지니스 오류처리
	 */
	@ExceptionHandler(BizException.class)
	public @ResponseBody ComResponseEntity<Void> handleBizException(BizException ex) throws Exception {
		errorLogging(ex);
		ComResultDto resultDto = null;
		if (ex.getArrayReplace() != null) {
			resultDto = getResultDto(ex.getYmlKey(), ex.getArrayReplace().toArray());
		} else {
			resultDto = getResultDto(ex.getYmlKey());
		}

		int statusCode = Integer.valueOf(resultDto.getStatus());
		ComResponseDto<Void> result = new ComResponseDto<Void>();
		result.setHttpStatusCode(statusCode);
		result.setCode(resultDto.getCode());
		result.setMessage(resultDto.getMessage());
		return new ComResponseEntity<Void>(result, HttpStatus.valueOf(statusCode));
	}

	/**
	 *
	 * @methodName : handleNoHandlerFoundException
	 * @author : cwcho
	 * @param ex
	 * @return
	 * @throws Exception
	 * @return : ComResponseEntity<Void>
	 * @description : Exception클래스명 기준으로 미리 정의한 메세지 및 코드를 리턴
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody ComResponseEntity<Void> handleNoHandlerFoundException(Exception ex) throws Exception {
		errorLogging(ex);
		ComResultDto resultDto = getResultDto(ex.getClass().getName());
		int statusCode = Integer.valueOf(resultDto.getStatus());
		ComResponseDto<Void> result = new ComResponseDto<Void>();
		result.setHttpStatusCode(statusCode);
		result.setCode(resultDto.getCode());
		result.setMessage(resultDto.getMessage());
		return new ComResponseEntity<Void>(result, HttpStatus.valueOf(statusCode));
	}

	private ComResultDto getResultDto(String ymlKey) {
		Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo(ymlKey);
		if (exceptionInfo == null) {
			return exceptionInfoConfig.getUndefinedErrorResult();
		}
		ComResultDto resultDesc = new ComResultDto();
		resultDesc.setCode(StringUtils.objectIfNullToEmpty(exceptionInfo.get("code")));
		resultDesc.setMessage(StringUtils.objectIfNullToEmpty(exceptionInfo.get("desc")));
		resultDesc.setStatus(StringUtils.objectIfNullToEmpty(exceptionInfo.get("status")));
		return resultDesc;
	}

	private ComResultDto getResultDto(String ymlKey, Object[] args) {
		Map<String, Object> exceptionInfo = exceptionInfoConfig.getExceptionInfo(ymlKey);
		if (exceptionInfo == null) {
			return exceptionInfoConfig.getUndefinedErrorResult();
		}

		String desc = StringUtils.objectIfNullToEmpty(exceptionInfo.get("desc"));
		ComResultDto resultDesc = new ComResultDto();
		resultDesc.setCode(StringUtils.objectIfNullToEmpty(exceptionInfo.get("code")));
		resultDesc.setMessage(MessageFormat.format(desc, args));
		resultDesc.setStatus(StringUtils.objectIfNullToEmpty(exceptionInfo.get("status")));
		return resultDesc;
	}

	private void errorLogging(Exception e) {
		logger.error("[ERROR MESSEAGE : {}]", ExceptionUtils.getStackTrace(e));
	}
}
