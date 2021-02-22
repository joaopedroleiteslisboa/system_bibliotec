package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CnpjInvalidoException extends RuntimeException {


    public CnpjInvalidoException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CnpjInvalidoException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public CnpjInvalidoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public CnpjInvalidoException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public CnpjInvalidoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
