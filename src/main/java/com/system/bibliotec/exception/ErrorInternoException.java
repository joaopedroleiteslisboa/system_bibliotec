package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoException extends RuntimeException{


	
	public ErrorInternoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorInternoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ErrorInternoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ErrorInternoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ErrorInternoException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
}
