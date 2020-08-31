package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CodBarrasExistenteException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1444094131813813792L;
	
	public CodBarrasExistenteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CodBarrasExistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CodBarrasExistenteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CodBarrasExistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CodBarrasExistenteException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}



	
	
	
}
