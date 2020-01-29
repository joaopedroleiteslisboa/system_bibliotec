package com.system.bibliotec.exception;

import org.springframework.security.core.AuthenticationException;

public class UsuarioNaoAtivadoException extends AuthenticationException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -351273455234989862L;

	public UsuarioNaoAtivadoException(String message) {
	        super(message);
	    }

	    public UsuarioNaoAtivadoException(String message, Throwable t) {
	        super(message, t);
	    }



}
