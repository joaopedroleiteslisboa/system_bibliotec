package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LivroExistenteException extends RuntimeException {


    public LivroExistenteException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LivroExistenteException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public LivroExistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = -1490188105572169162L;

    public LivroExistenteException(String message) {
        super(message);
    }

    public LivroExistenteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
