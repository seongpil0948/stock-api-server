package com.stock.sp.apiserver.common.dto.web;

import com.stock.sp.apiserver.common.exception.BizException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComResponseDto<T> extends ComResultDto {

	public ComResponseDto() {
	}

	public ComResponseDto(T body) {
		this.setBody(body);
	}

	private T body;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		// 조회결과가 null 일경우, 404 조회결과가 없습니다. 리턴
		// HttpMethod.GET.toString().equals(((ServletRequestAttributes)
		// RequestContextHolder.getRequestAttributes()).getRequest().getMethod())
		if (body == null) {
			throw new BizException("data_not_found");
		}
		this.body = body;
	}

}
