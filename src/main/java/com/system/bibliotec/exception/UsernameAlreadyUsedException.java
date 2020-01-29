package com.system.bibliotec.exception;

public class UsernameAlreadyUsedException extends RuntimeException {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 7018924736134935244L;

	public UsernameAlreadyUsedException() {
	        super(" nome de Usuario (username) ja utilizado");
	    }

}
