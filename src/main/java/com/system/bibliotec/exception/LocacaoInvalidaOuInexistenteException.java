package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocacaoInvalidaOuInexistenteException extends RuntimeException {

    public LocacaoInvalidaOuInexistenteException() {
        super();
        // TODO Auto-generated constructor stub
    }


    public LocacaoInvalidaOuInexistenteException(String message, Throwable cause, boolean enableSuppression,
                                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }


    public LocacaoInvalidaOuInexistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    /**
     *
     */
    private static final long serialVersionUID = -4185069483240380458L;


    public LocacaoInvalidaOuInexistenteException(String message) {
        super(message);
    }


    public LocacaoInvalidaOuInexistenteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
