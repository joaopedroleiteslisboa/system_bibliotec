package com.system.bibliotec.config;

import com.system.bibliotec.model.enums.Idioma;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.model.enums.StatusLocacao;
import com.system.bibliotec.model.enums.StatusReserva;

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
	
	public static final Idioma DEFAULT_VALUE_IDIOMA_LIVRO = Idioma.PORTUGUES;
	
	public static final StatusLocacao DEFAULT_VALUE_STATUSLOCACAO = StatusLocacao.ATIVA;
	
	public static final StatusReserva DEFAULT_VALUE_STATUSRESERVA = StatusReserva.ATIVA;
	
	public static final int DEFAULT_VALUE_QUANTIDADE_LOCACAO = 0;
	
	public static final String DEFAULT_VALUE_URL_PHOTOS_BOOK = "https://picsum.photos/id/";
	
	public static final String PHOTOS_BOOK_LENGTH_WIDTH_200_X_300 = "/200/300";
	


	private ConstantsUtils() {
	}
}

