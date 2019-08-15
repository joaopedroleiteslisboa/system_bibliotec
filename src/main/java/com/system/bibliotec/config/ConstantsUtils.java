package com.system.bibliotec.config;

import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.model.enums.StatusLocacao;

/**
 * Application constants.
 */
public final class ConstantsUtils {

	// Regex for acceptable logins
	public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

	public static final String SYSTEM_ACCOUNT = "system";
	
	public static final String DEFAULT_LANGUAGE = "pt-br";
	
	public static final String ANONYMOUS_USER = "anonymoususer";
	
	public  static final StatusCliente DEFAULT_VALUE_STATUSCLIENTE = StatusCliente.ADIMPLENTE;
	
	public static final boolean DEFAULT_VALUE_ATIVO = true;
	
	public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO = StatusLivro.LIVRE;
	
	public static final StatusLocacao DEFAULT_VALUE_STATUSLOCACAO = StatusLocacao.ATIVA;
	
	public static final int DEFAULT_VALUE_QUANTIDADE_LOCACAO = 0;
	
	

	private ConstantsUtils() {
	}
}
