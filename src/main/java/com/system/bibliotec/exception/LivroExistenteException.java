package com.system.bibliotec.exception;

public class LivroExistenteException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1490188105572169162L;

	public LivroExistenteException(String message) {
		super(message);
	}
	
	public LivroExistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
