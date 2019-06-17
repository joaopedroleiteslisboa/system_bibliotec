package com.system.bibliotec.exception;

public class ClienteInativoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1801654357679967879L;

	public ClienteInativoException(String message) {
		super(message);
	}

	public ClienteInativoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
