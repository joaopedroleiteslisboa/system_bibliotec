package com.system.bibliotec.exception;

public class ReservaUpdateException extends RuntimeException {

	public ReservaUpdateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservaUpdateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ReservaUpdateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6468711606428633445L;

	public ReservaUpdateException(String message) {
		super(message);
	}

	public ReservaUpdateException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
