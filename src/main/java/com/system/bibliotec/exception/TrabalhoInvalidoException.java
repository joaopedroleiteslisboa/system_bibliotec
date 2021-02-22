package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrabalhoInvalidoException extends AgendadorException {

    public TrabalhoInvalidoException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public TrabalhoInvalidoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public TrabalhoInvalidoException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public TrabalhoInvalidoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
