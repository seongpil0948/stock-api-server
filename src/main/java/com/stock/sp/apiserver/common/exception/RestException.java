package kr.co.dsi.common.exception;

import java.util.List;

public class RestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String ymlKey;
	private List<String> arrayReplace;
	private Throwable throwable = null;

	public RestException(Throwable throwable) {
		this.throwable = throwable;
	}

	public RestException(String ymlKey) {
		this.ymlKey = ymlKey;
	}

	public RestException(Throwable throwable, String ymlKey) {
		this.throwable = throwable;
		this.ymlKey = ymlKey;
	}

	public RestException(String ymlKey, List<String> arrayReplace) {
		this.ymlKey = ymlKey;
		this.arrayReplace = arrayReplace;
	}

	public RestException(Throwable throwable, String ymlKey, List<String> arrayReplace) {
		this.throwable = throwable;
		this.ymlKey = ymlKey;
		this.arrayReplace = arrayReplace;
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

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
