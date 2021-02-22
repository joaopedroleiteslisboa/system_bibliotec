package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OperacaoCanceladaException extends RuntimeException {

    public OperacaoCanceladaException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public OperacaoCanceladaException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    public OperacaoCanceladaException(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public OperacaoCanceladaException() {
        // TODO Auto-generated constructor stub
    }

}
