package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OperacaoJaAtivadaParaUsuarioException extends RuntimeException{
	
	public OperacaoJaAtivadaParaUsuarioException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public OperacaoJaAtivadaParaUsuarioException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }



    public OperacaoJaAtivadaParaUsuarioException(String string) {
        // TODO Auto-generated constructor stub
        super(string);
    }

    public OperacaoJaAtivadaParaUsuarioException() {
        // TODO Auto-generated constructor stub
    }

}
