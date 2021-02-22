package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocacaoLimiteDataException extends RuntimeException {

    public LocacaoLimiteDataException() {
        super();
        // TODO Auto-generated constructor stub
    }


    public LocacaoLimiteDataException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }


    public LocacaoLimiteDataException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    /**
     *
     */
    private static final long serialVersionUID = -3941379359502442873L;

    public LocacaoLimiteDataException(String message) {
        super(message);
    }


    public LocacaoLimiteDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

