package com.system.bibliotec.exception;

public class LivroAvariadoException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5412153690545454751L;

	public LivroAvariadoException(String message) {
		super(message);
	}
	
	public LivroAvariadoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
