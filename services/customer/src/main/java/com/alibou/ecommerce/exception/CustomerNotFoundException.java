package com.alibou.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class CustomerNotFoundException extends RuntimeException {
	private String msg;

	public CustomerNotFoundException(String message) {
		super(message);
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
