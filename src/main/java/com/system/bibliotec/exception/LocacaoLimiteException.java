package com.system.bibliotec.exception;

public class LocacaoLimiteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3931343226130294191L;

	public LocacaoLimiteException(String message) {
		super(message);
	}

	

	public LocacaoLimiteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
