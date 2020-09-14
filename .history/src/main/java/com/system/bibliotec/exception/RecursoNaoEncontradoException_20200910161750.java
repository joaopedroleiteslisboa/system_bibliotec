package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {


    public RecursoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public RecursoNaoEncontradoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


    public RecursoNaoEncontradoException(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public RecursoNaoEncontradoException() {
        // TODO Auto-generated constructor stub
    }