package com.system.bibliotec.config;

import com.system.bibliotec.model.enums.*;

/**
 * Application constants.
 */
public final class ConstantsUtils {

	// user@domain.com : true
	// user@domain.co.in : true
	// user.name@domain.com : true
	// user_name@domain.com : true
	// username@yahoo.corporate.in : true

	// .username@yahoo.com : false
	// username@yahoo.com. : false
	// username@yahoo..com : false
	// username@yahoo.c : false
	// username@yahoo.corporate : false

	public static final String REGEX_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	public static final String SYSTEM_ACCOUNT = "system";

	public static final String ANONYMOUS_USER = "anonymoususer";

	public static final StatusCliente DEFAULT_VALUE_STATUSCLIENTE = StatusCliente.ADIMPLENTE;

	public static final String DEFAULT_LANGUAGE = "pt-br";

	public static final boolean DEFAULT_VALUE_ATIVO = true;

	public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO_LIVRE = StatusLivro.LIVRE;

	public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO_RESERVADO = StatusLivro.RESERVADO;

	public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO_LOCADO = StatusLivro.LOCADO;

	public static final Idioma DEFAULT_VALUE_IDIOMA_LIVRO = Idioma.PORTUGUES;

	public static final StatusLocacao DEFAULT_VALUE_STATUSLOCACAO_ATIVA = StatusLocacao.ATIVA;

	public static final StatusLocacao DEFAULT_VALUE_STATUSLOCACAO_CANCELADA = StatusLocacao.CANCELADA;

	public static final StatusLocacao DEFAULT_VALUE_STATUSLOCACAO_FINALIZADA = StatusLocacao.FINALIZADA;

	public static final StatusReserva DEFAULT_VALUE_STATUSRESERVA_ATIVA = StatusReserva.ATIVA;

	public static final StatusReserva DEFAULT_VALUE_STATUSRESERVA_CANCELADA = StatusReserva.CANCELADA;

	public static final int DEFAULT_VALUE_QUANTIDADE_LOCACAO = 1;

	public static final int DEFAULT_VALUE_QUANTIDADE_RENOVACAO_MAXIMA_LOCACAO = 3;

	public static final int DEFAULT_VALUE_QUANTIDADE_LOCACAO_INICIAL = 0;

	public static final String DEFAULT_VALUE_URL_PHOTOS_BOOK = "https://picsum.photos/id/";

	public static final String PHOTOS_BOOK_LENGTH_WIDTH_200_X_300 = "/200/300";

	public static final int DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO = 1;

	public static final int DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO = 1;

	private ConstantsUtils() {
	}
}
