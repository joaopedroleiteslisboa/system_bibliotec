package com.system.bibliotec.exception;

public class RequisicaoException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = -9037210349722240539L;

    public RequisicaoException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public RequisicaoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public RequisicaoException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public RequisicaoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}