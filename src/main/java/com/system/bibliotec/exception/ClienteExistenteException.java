package com.system.bibliotec.exception;

public class ClienteExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037210349716540539L;

	public ClienteExistenteException(String message) {
		super(message);
	}

	public ClienteExistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
