package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimiteReservaAtingida extends RuntimeException {

    public LimiteReservaAtingida(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public LimiteReservaAtingida(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    public LimiteReservaAtingida(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public LimiteReservaAtingida() {
        // TODO Auto-generated constructor stub
    }
}
