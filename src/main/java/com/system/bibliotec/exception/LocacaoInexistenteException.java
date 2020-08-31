package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocacaoInexistenteException extends RuntimeException {

	public LocacaoInexistenteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocacaoInexistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LocacaoInexistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 57344536557689554L;

	public LocacaoInexistenteException(String message) {
		super(message);
	}
	
	public LocacaoInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}