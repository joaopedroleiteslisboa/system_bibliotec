package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
