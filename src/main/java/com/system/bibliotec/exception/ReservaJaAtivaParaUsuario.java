package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaJaAtivaParaUsuario extends RuntimeException {


    public ReservaJaAtivaParaUsuario(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ReservaJaAtivaParaUsuario(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    public ReservaJaAtivaParaUsuario(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public ReservaJaAtivaParaUsuario() {
        // TODO Auto-generated constructor stub
    }
}
