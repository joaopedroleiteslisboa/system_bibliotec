package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimiteOperacaoAtingidaException extends RuntimeException {

	 public LimiteOperacaoAtingidaException(String message, Throwable cause) {
	        super(message, cause);
	        // TODO Auto-generated constructor stub
	    }

	    public LimiteOperacaoAtingidaException(Throwable cause) {
	        super(cause);
	        // TODO Auto-generated constructor stub
	    }



	    public LimiteOperacaoAtingidaException(String string) {
	        // TODO Auto-generated constructor stub
	        super(string);
	    }

	    public LimiteOperacaoAtingidaException() {
	        // TODO Auto-generated constructor stub
	    }
}
