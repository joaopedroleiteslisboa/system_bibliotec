package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SEE_OTHER)
public class EstoqueInsuficienteException extends RuntimeException {

	public EstoqueInsuficienteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EstoqueInsuficienteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EstoqueInsuficienteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3556158455142066479L;

	public EstoqueInsuficienteException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	public EstoqueInsuficienteException() {
		// TODO Auto-generated constructor stub
	}

}
