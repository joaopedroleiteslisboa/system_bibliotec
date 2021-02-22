package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsbnInvalidoException extends RuntimeException {

    public IsbnInvalidoException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public IsbnInvalidoException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public IsbnInvalidoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = -7342121774138709915L;

    public IsbnInvalidoException(String message) {
        super(message);
    }

    public IsbnInvalidoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
