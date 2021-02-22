package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioNaoAtivadoException extends RuntimeException {

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
