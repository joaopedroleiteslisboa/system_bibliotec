package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CargoInexistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5774956498518284265L;

	public CargoInexistenteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CargoInexistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CargoInexistenteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CargoInexistenteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CargoInexistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
