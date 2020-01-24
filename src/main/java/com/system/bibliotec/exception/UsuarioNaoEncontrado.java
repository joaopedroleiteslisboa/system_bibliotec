package com.system.bibliotec.exception;

public class UsuarioNaoEncontrado extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5178412313776204449L;

	public UsuarioNaoEncontrado(String message) {
		super(message);
	}

	public UsuarioNaoEncontrado(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UsuarioNaoEncontrado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioNaoEncontrado(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsuarioNaoEncontrado(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
