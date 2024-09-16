package com.alibou.ecommerce.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

	private final String msg;

	public BusinessException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
}
