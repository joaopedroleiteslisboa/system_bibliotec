package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyUsedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7018924736134935244L;

    public UsernameAlreadyUsedException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public UsernameAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public UsernameAlreadyUsedException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    public UsernameAlreadyUsedException(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public UsernameAlreadyUsedException() {
        // TODO Auto-generated constructor stub
    }
}
