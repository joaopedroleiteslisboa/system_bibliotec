package com.system.bibliotec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EncerramentoOperacaoLocacaoException extends RuntimeException {

	public EncerramentoOperacaoLocacaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EncerramentoOperacaoLocacaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EncerramentoOperacaoLocacaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094045346380257138L;

	public EncerramentoOperacaoLocacaoException(String message) {
		super(message);
	}

	public EncerramentoOperacaoLocacaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
