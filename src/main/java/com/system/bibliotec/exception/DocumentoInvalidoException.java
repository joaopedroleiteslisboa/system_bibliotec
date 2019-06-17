package com.system.bibliotec.exception;

public class DocumentoInvalidoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3408312523556553352L;

	public DocumentoInvalidoException(String message) {
		super(message);
	}

	public DocumentoInvalidoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
