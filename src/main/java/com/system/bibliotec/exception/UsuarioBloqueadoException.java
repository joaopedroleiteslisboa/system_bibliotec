package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioBloqueadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4555027824004401270L;

	public UsuarioBloqueadoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioBloqueadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsuarioBloqueadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UsuarioBloqueadoException(String message) {
		super(message);
	}

	public UsuarioBloqueadoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
