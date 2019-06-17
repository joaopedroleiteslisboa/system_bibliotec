package com.system.bibliotec.exception;

public class ReservaInexistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 57344536557689336L;

	public ReservaInexistenteException(String message) {
		super(message);
	}
	
	public ReservaInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
