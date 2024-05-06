package com.stock.sp.apiserver.common.exception;

import com.stock.sp.apiserver.common.dto.web.ComResultDto;

import java.util.List;

/**
 *
 * @programName : BizException
 * @author : cwcho
 * @description : 업무 오류 Exception
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String ymlKey;
	private List<String> arrayReplace;
	private Throwable throwable = null;
	private ComResultDto resultDto;

	public BizException(ComResultDto resultDto) {
		this.resultDto = resultDto;
	}

	public BizException(Throwable throwable, ComResultDto resultDto) {
		this.throwable = throwable;
		this.resultDto = resultDto;
	}

	public BizException(String ymlKey) {
		this.ymlKey = ymlKey;
	}

	public BizException(Throwable throwable, String ymlKey) {
		this.throwable = throwable;
		this.ymlKey = ymlKey;
	}

	public BizException(String ymlKey, List<String> arrayReplace) {
		this.ymlKey = ymlKey;
		this.arrayReplace = arrayReplace;
	}

	public BizException(Throwable throwable, String ymlKey, List<String> arrayReplace) {
		this.throwable = throwable;
		this.ymlKey = ymlKey;
		this.arrayReplace = arrayReplace;
	}

	public BizException(String code, String message, String status) {
		this.resultDto = new ComResultDto();
		resultDto.setCode(code);
		resultDto.setMessage(message);
		resultDto.setStatus(status);
	}

	public BizException(Throwable throwable, String code, String message, String status) {
		this.throwable = throwable;
		this.resultDto = new ComResultDto();
		resultDto.setCode(code);
		resultDto.setMessage(message);
		resultDto.setStatus(status);
	}

	public String getYmlKey() {
		return ymlKey;
	}

	public void setYmlKey(String ymlKey) {
		this.ymlKey = ymlKey;
	}

	public List<String> getArrayReplace() {
		return arrayReplace;
	}

	public void setArrayReplace(List<String> arrayReplace) {
		this.arrayReplace = arrayReplace;
	}

	public ComResultDto getResultDto() {
		return resultDto;
	}

	public void setResultDto(ComResultDto resultDto) {
		this.resultDto = resultDto;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
