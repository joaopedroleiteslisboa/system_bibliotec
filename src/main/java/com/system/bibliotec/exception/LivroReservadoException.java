package com.system.bibliotec.exception;

public class LivroReservadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8094427135739692105L;


	public LivroReservadoException(String message) {
		super(message);
	}

	

	public LivroReservadoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
