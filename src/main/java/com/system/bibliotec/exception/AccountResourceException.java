package com.system.bibliotec.exception;

public class AccountResourceException extends RuntimeException {


    public AccountResourceException(String message) {
        super(message);
    }

    public AccountResourceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AccountResourceException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AccountResourceException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public AccountResourceException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


}
