package com.system.bibliotec.exception;

public class IsbnInvalidoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7342121774138709915L;

	public IsbnInvalidoException(String message) {
		super(message);
	}
	
	public IsbnInvalidoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
