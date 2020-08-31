package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocacaoJaAtivaParaUsuario extends RuntimeException {

	
	 public LocacaoJaAtivaParaUsuario(String message, Throwable cause) {
	        super(message, cause);
	        // TODO Auto-generated constructor stub
	    }

	    public LocacaoJaAtivaParaUsuario(Throwable cause) {
	        super(cause);
	        // TODO Auto-generated constructor stub
	    }


	    public LocacaoJaAtivaParaUsuario(String string) {
	        // TODO Auto-generated constructor stub
	        super(string);
	    }

	    public LocacaoJaAtivaParaUsuario() {
	        // TODO Auto-generated constructor stub
	    }
}
