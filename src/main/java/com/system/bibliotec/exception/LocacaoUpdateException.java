package com.system.bibliotec.exception;

public class LocacaoUpdateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6269549753590100021L;

	public LocacaoUpdateException(String message) {
		super(message);
	}

	

	public LocacaoUpdateException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
