package com.system.bibliotec.exception;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public class ClienteExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037210349716540539L;

	public ClienteExistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ClienteExistenteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ClienteExistenteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ClienteExistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


	
}
