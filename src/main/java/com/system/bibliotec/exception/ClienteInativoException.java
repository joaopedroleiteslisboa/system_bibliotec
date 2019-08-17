package com.system.bibliotec.exception;

public class ClienteInativoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1801654357679967879L;

	public ClienteInativoException(String message) {
		super(message);
	}

	public ClienteInativoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ClienteInativoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteInativoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ClienteInativoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
