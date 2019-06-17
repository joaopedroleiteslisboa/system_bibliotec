package com.system.bibliotec.exception;

public class ClienteInexistenteException extends RuntimeException {

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
