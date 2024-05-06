package com.stock.sp.apiserver.spring;

import com.stock.sp.apiserver.common.dto.web.ComResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ComResponseEntity<T> extends ResponseEntity<ComResponseDto<T>> {

	public ComResponseEntity(ComResponseDto<T> body, HttpStatus status) {
		super(body, status);
		body.setHttpStatusCode(status.value());
	}

	public ComResponseEntity(HttpStatus unauthorized) {
		super(unauthorized);
	}
}
