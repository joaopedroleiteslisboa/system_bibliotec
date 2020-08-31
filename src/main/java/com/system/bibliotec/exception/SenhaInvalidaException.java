package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SEE_OTHER)
public class SenhaInvalidaException extends RuntimeException {

	public SenhaInvalidaException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SenhaInvalidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SenhaInvalidaException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SenhaInvalidaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SenhaInvalidaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
