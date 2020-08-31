package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaInexistenteException extends RuntimeException {

	public ReservaInexistenteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservaInexistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ReservaInexistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 57344536557689336L;

	public ReservaInexistenteException(String message) {
		super(message);
	}
	
	public ReservaInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
