package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocacaoUpdateException extends RuntimeException {

    public LocacaoUpdateException() {
        super();
        // TODO Auto-generated constructor stub
    }


    public LocacaoUpdateException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }


    public LocacaoUpdateException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    /**
     *
     */
    private static final long serialVersionUID = -6269549753590100021L;

    public LocacaoUpdateException(String message) {
        super(message);
    }


    public LocacaoUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
