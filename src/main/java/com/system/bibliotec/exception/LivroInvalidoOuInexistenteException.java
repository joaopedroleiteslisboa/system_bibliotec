package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroInvalidoOuInexistenteException extends RuntimeException {

	public LivroInvalidoOuInexistenteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroInvalidoOuInexistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LivroInvalidoOuInexistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094045346380257138L;

	public LivroInvalidoOuInexistenteException(String message) {
		super(message);
	}

	public LivroInvalidoOuInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
