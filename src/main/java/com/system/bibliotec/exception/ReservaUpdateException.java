package com.system.bibliotec.exception;

public class ReservaUpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6468711606428633445L;

	public ReservaUpdateException(String message) {
		super(message);
	}

	public ReservaUpdateException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
