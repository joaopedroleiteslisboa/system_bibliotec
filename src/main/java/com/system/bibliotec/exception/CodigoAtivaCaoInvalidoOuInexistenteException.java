package com.system.bibliotec.exception;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CodigoAtivaCaoInvalidoOuInexistenteException extends RuntimeException {


    public CodigoAtivaCaoInvalidoOuInexistenteException(String message, Throwable cause, boolean enableSuppression,
                                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public CodigoAtivaCaoInvalidoOuInexistenteException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public CodigoAtivaCaoInvalidoOuInexistenteException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public CodigoAtivaCaoInvalidoOuInexistenteException(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }


}
