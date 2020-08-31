package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LivroReservadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6281820901793000309L;

	public LivroReservadoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroReservadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LivroReservadoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LivroReservadoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LivroReservadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	




	
}
