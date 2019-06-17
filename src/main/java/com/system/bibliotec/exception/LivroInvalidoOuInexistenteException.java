package com.system.bibliotec.exception;

public class LivroInvalidoOuInexistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094045346380257138L;

	public LivroInvalidoOuInexistenteException(String message) {
		super(message);
	}

	public LivroInvalidoOuInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
