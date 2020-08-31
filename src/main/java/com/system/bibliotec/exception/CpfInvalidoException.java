package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfInvalidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 585588974722360052L;

	public CpfInvalidoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CpfInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CpfInvalidoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CpfInvalidoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CpfInvalidoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}
