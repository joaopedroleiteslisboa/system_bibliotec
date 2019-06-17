package com.system.bibliotec.exception;

public class ClienteInadimplenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1801654357679967879L;

	public ClienteInadimplenteException(String message) {
		super(message);
	}

	public ClienteInadimplenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
