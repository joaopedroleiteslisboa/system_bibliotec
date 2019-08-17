package com.system.bibliotec.exception;

public class ClienteInexistenteException extends RuntimeException {

	public ClienteInexistenteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteInexistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ClienteInexistenteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -236295735102594079L;

	public ClienteInexistenteException(String message) {
		super(message);
	}

	public ClienteInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
