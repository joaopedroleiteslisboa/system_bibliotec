package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeRenovacaoLocacaoLimiteException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5287166878520235121L;

    public QuantidadeRenovacaoLocacaoLimiteException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public QuantidadeRenovacaoLocacaoLimiteException(String message, Throwable cause, boolean enableSuppression,
                                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public QuantidadeRenovacaoLocacaoLimiteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    public QuantidadeRenovacaoLocacaoLimiteException(String message) {
        super(message);
    }

    public QuantidadeRenovacaoLocacaoLimiteException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
