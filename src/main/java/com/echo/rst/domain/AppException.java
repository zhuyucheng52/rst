package com.echo.rst.domain;

/**
 * Created by echo on 16-6-26.
 */
public class AppException extends RuntimeException {
	private Integer errorCode;
	private String errorMsg;

	public AppException(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public AppException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String getMessage() {
		return (errorCode == null ? errorMsg: errorCode) + " " +super.getMessage();
	}
}
