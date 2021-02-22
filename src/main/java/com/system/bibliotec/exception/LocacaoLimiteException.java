package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocacaoLimiteException extends RuntimeException {

    public LocacaoLimiteException() {
        super();
        // TODO Auto-generated constructor stub
    }


    public LocacaoLimiteException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }


    public LocacaoLimiteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    /**
     *
     */
    private static final long serialVersionUID = -3931343226130294191L;

    public LocacaoLimiteException(String message) {
        super(message);
    }


    public LocacaoLimiteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
