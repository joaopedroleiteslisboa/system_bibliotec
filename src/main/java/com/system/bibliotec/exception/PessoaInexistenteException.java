package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaInexistenteException extends RuntimeException {

    public PessoaInexistenteException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PessoaInexistenteException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public PessoaInexistenteException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public PessoaInexistenteException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public PessoaInexistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
