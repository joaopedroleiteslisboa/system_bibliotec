package com.system.bibliotec.exception;

public class LivroLocadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3378059978201221127L;

	public LivroLocadoException(String message) {
		super(message);
	}
	
	public LivroLocadoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
