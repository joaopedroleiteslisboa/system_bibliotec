package com.system.bibliotec.exception;

public class LivroAvariadoException extends RuntimeException {


	public LivroAvariadoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroAvariadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public LivroAvariadoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5412153690545454751L;

	public LivroAvariadoException(String message) {
		super(message);
	}
	
	public LivroAvariadoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
