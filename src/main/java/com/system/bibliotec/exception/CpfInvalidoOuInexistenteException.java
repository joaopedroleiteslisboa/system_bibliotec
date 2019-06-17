package com.system.bibliotec.exception;

public class CpfInvalidoOuInexistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6056628009587245966L;
	
	
	public CpfInvalidoOuInexistenteException(String message) {
		super(message);
	}

	public CpfInvalidoOuInexistenteException(String msg, Throwable cause) {
		super(msg, cause);
	}


}
