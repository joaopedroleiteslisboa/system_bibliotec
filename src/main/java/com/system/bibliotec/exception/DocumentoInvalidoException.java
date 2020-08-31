package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DocumentoInvalidoException extends RuntimeException {

	public DocumentoInvalidoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentoInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DocumentoInvalidoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

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
