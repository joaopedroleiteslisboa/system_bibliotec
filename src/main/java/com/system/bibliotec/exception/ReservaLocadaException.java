package com.system.bibliotec.exception;

public class ReservaLocadaException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8112067182328892670L;



	public ReservaLocadaException(String message) {
		super(message);
	}

	

	public ReservaLocadaException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
