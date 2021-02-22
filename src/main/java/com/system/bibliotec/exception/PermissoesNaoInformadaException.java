package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PermissoesNaoInformadaException extends RuntimeException {


    public PermissoesNaoInformadaException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PermissoesNaoInformadaException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public PermissoesNaoInformadaException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public PermissoesNaoInformadaException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public PermissoesNaoInformadaException(String string) {
        // TODO Auto-generated constructor stub
    }
}
