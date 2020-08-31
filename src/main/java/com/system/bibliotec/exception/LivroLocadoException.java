package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LivroLocadoException extends RuntimeException {

	public LivroLocadoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroLocadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LivroLocadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3378059978201221127L;

	public LivroLocadoException(String message) {
		super(message);
	}
	
	public LivroLocadoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
