package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfInvalidoOuInexistenteException extends RuntimeException {

    public CpfInvalidoOuInexistenteException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CpfInvalidoOuInexistenteException(String message, Throwable cause, boolean enableSuppression,
                                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public CpfInvalidoOuInexistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = 6056628009587245966L;


    public CpfInvalidoOuInexistenteException(String message) {
        super(message);
    }

    public CpfInvalidoOuInexistenteException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
