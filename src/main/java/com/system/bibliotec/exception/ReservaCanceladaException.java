package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaCanceladaException extends RuntimeException {


    public ReservaCanceladaException() {
        super();
        // TODO Auto-generated constructor stub
    }


    public ReservaCanceladaException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }


    public ReservaCanceladaException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    /**
     *
     */
    private static final long serialVersionUID = -2590070841347341762L;


    public ReservaCanceladaException(String message) {
        super(message);
    }


    public ReservaCanceladaException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
