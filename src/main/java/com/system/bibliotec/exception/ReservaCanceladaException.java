package com.system.bibliotec.exception;

public class ReservaCanceladaException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2590070841347341762L;



	public ReservaCanceladaException(String message) {
		super(message);
	}

	

	public ReservaCanceladaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
