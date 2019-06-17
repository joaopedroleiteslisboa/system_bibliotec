package com.system.bibliotec.exception;

public class LocacaoInvalidaOuInexistenteException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4185069483240380458L;



	public LocacaoInvalidaOuInexistenteException(String message) {
		super(message);
	}

	

	public LocacaoInvalidaOuInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
