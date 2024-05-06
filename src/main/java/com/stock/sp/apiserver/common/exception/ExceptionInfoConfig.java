package com.stock.sp.apiserver.common.exception;

import com.stock.sp.apiserver.common.dto.web.ComResultDto;
import com.stock.sp.apiserver.common.utils.StringUtils;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.text.MessageFormat;
import java.util.Map;

/**
 * ===========================================
 *
 * @SystemName : dmk
 * @ProgramName : ExceptionInfoConfig
 * @Author : Lavega
 * @CreateDate : 2020.06.10
 * @Version : 1.0
 * @Description :
 *              ===========================================
 */
public class ExceptionInfoConfig {
	private Map<String, Object> exceptionInfo = null;
	private ComResultDto successResultDto = null;
	private ComResultDto undefinedErrorResult = null;

	@SuppressWarnings("unchecked")
	public ExceptionInfoConfig(String filePath) {
		YamlMapFactoryBean yaml = new YamlMapFactoryBean();
		yaml.setResources(new ClassPathResource(filePath));
		this.exceptionInfo = yaml.getObject();
		Map<String, Object> successInfos = (Map<String, Object>) exceptionInfo.get("success");
		Map<String, Object> successInfo = (Map<String, Object>) successInfos.get("200ok");

		successResultDto = new ComResultDto();
		successResultDto.setCode(StringUtils.objectIfNullToEmpty(successInfo.get("code")));
		successResultDto.setMessage(StringUtils.objectIfNullToEmpty(successInfo.get("desc")));
		successResultDto.setStatus(StringUtils.objectIfNullToEmpty(successInfo.get("status")));

		Map<String, Object> exceptionInfos = (Map<String, Object>) exceptionInfo.get("exception");
		Map<String, Object> exceptionInfo = (Map<String, Object>) exceptionInfos.get("notdefine");

		undefinedErrorResult = new ComResultDto();
		undefinedErrorResult.setCode(StringUtils.objectIfNullToEmpty(exceptionInfo.get("code")));
		undefinedErrorResult.setMessage(StringUtils.objectIfNullToEmpty(exceptionInfo.get("desc")));
		undefinedErrorResult.setStatus(StringUtils.objectIfNullToEmpty(exceptionInfo.get("status")));
	}

	public Map<String, Object> getExceptionInfo() {
		return exceptionInfo;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getExceptionInfo(String ymlKey) {
		Map<String, Object> exceptionInfos = (Map<String, Object>) exceptionInfo.get("exception");
		Map<String, Object> exceptionInfo = (Map<String, Object>) exceptionInfos.get(ymlKey);
		return exceptionInfo;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getSuccessInfo(String ymlKey) {
		Map<String, Object> successInfos = (Map<String, Object>) exceptionInfo.get("success");
		Map<String, Object> successInfo = (Map<String, Object>) successInfos.get(ymlKey);
		return successInfo;
	}

	public ComResultDto getSuccessResult() {
		return this.successResultDto;
	}

	public ComResultDto getUndefinedErrorResult() {
		return this.undefinedErrorResult;
	}

	public ComResultDto getSuccessInfoReuslt(String ymlKey) {
		Map<String, Object> exceptionInfo = getSuccessInfo(ymlKey);

		ComResultDto result = new ComResultDto();
		result.setCode(String.valueOf(exceptionInfo.get("code")));
		result.setMessage((String) exceptionInfo.get("desc"));
		result.setStatus(String.valueOf(exceptionInfo.get("status")));

		return result;

	}

	@SuppressWarnings("unchecked")
	public ComResultDto getExceptionInfoResult(String ymlKey) {
		Map<String, Object> exceptionInfos = (Map<String, Object>) exceptionInfo.get("exception");
		Map<String, Object> exceptionInfo = (Map<String, Object>) exceptionInfos.get(ymlKey);

		ComResultDto result = new ComResultDto();
		result.setCode((String) exceptionInfo.get("code"));
		result.setMessage((String) exceptionInfo.get("desc"));
		result.setStatus((String) exceptionInfo.get("status"));

		return result;
	}

	public ComResultDto getResultDto(String ymlKey) {
		Map<String, Object> exceptionInfo = getExceptionInfo(ymlKey);
		if (exceptionInfo == null) {
			return getUndefinedErrorResult();
		}
		ComResultDto resultDesc = new ComResultDto();
		resultDesc.setCode(StringUtils.ifNullToEmpty(exceptionInfo.get("code")));
		resultDesc.setMessage(StringUtils.ifNullToEmpty(exceptionInfo.get("desc")));
		resultDesc.setStatus(StringUtils.ifNullToEmpty(exceptionInfo.get("status")));
		return resultDesc;
	}

	public ComResultDto getResultDto(String ymlKey, Object[] args) {
		ComResultDto resultDesc = getResultDto(ymlKey);
		String desc = StringUtils.ifNullToEmpty(resultDesc.getMessage());
		resultDesc.setMessage(MessageFormat.format(desc, args));
		return resultDesc;
	}
}
