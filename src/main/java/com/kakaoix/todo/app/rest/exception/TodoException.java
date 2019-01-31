package com.kakaoix.todo.app.rest.exception;

public class TodoException extends RuntimeException {
	private static final long serialVersionUID = 4143200102791804848L;

	private String code;
	
	public TodoException(String m, String code) {
		super(m);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
