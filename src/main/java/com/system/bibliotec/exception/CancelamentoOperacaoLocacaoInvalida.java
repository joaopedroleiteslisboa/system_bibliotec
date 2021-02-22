package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CancelamentoOperacaoLocacaoInvalida extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public CancelamentoOperacaoLocacaoInvalida() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CancelamentoOperacaoLocacaoInvalida(String message, Throwable cause, boolean enableSuppression,
                                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public CancelamentoOperacaoLocacaoInvalida(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public CancelamentoOperacaoLocacaoInvalida(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public CancelamentoOperacaoLocacaoInvalida(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

}
