package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public EmailAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public EmailAlreadyUsedException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = -3556158455142066479L;

    public EmailAlreadyUsedException(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public EmailAlreadyUsedException() {
        // TODO Auto-generated constructor stub
    }
}
