package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimiteLocacaoAtingida extends RuntimeException{

	 public LimiteLocacaoAtingida(String message, Throwable cause) {
	        super(message, cause);
	        // TODO Auto-generated constructor stub
	    }

	    public LimiteLocacaoAtingida(Throwable cause) {
	        super(cause);
	        // TODO Auto-generated constructor stub
	    }



	    public LimiteLocacaoAtingida(String string) {
	        // TODO Auto-generated constructor stub
	        super(string);
	    }

	    public LimiteLocacaoAtingida() {
	        // TODO Auto-generated constructor stub
	    }
}
