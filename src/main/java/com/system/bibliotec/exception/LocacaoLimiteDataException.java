package com.system.bibliotec.exception;

public class LocacaoLimiteDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3941379359502442873L;

	public LocacaoLimiteDataException(String message) {
		super(message);
	}

	

	public LocacaoLimiteDataException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

