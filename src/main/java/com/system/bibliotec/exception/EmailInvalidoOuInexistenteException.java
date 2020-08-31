package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailInvalidoOuInexistenteException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6450816969555682660L;

    public EmailInvalidoOuInexistenteException() {
    super();
    // TODO Auto-generated constructor stub
}

    public EmailInvalidoOuInexistenteException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public EmailInvalidoOuInexistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }



    public EmailInvalidoOuInexistenteException(String message) {
        super(message);
    }

    public EmailInvalidoOuInexistenteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}