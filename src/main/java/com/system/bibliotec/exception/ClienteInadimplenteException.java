package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteInadimplenteException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1801654357679967879L;

    public ClienteInadimplenteException(String message) {
        super(message);
    }

    public ClienteInadimplenteException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ClienteInadimplenteException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ClienteInadimplenteException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public ClienteInadimplenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
