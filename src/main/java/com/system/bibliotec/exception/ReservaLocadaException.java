package com.system.bibliotec.exception;

public class ReservaLocadaException extends RuntimeException {

	

	public ReservaLocadaException() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ReservaLocadaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}



	public ReservaLocadaException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -8112067182328892670L;



	public ReservaLocadaException(String message) {
		super(message);
	}

	

	public ReservaLocadaException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
