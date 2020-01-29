package com.system.bibliotec.exception;

public class EmailAlreadyUsedException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -764368594712227863L;

	/**
	 * 
	 */


	public EmailAlreadyUsedException() {
	        super("Email ja usado!");
	    }
}
