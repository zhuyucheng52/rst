package com.echo.rst.domain;

/**
 * Created by echo on 16-6-26.
 */
public class AppException extends RuntimeException {
	private Integer errorCode;
	private String errorMsg;

	private final static ErrorCodesResolver resolver = new ErrorCodesResolver();

	public AppException(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public AppException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String getMessage() {
		if (errorMsg != null) {
			return errorMsg;
		} else {
			return resolver.getValue(this.errorCode + "");
		}
	}
}
