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

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String N_A = "N/A";

    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final StatusPessoa DEFAULT_VALUE_STATUSPESSOA = StatusPessoa.ADIMPLENTE;

    public static final StatusPessoa DEFAULT_VALUE_STATUS_USUARIO_INADIMPLENTE = StatusPessoa.INADIMPLENTE;

    public static final String DEFAULT_LANGUAGE = "pt-br";

    public static final boolean DEFAULT_VALUE_ATIVO = true;

    //public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO_LIVRE = StatusLivro.LIVRE;

    //public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO_RESERVADO = StatusLivro.RESERVADO;

    //public static final StatusLivro DEFAULT_VALUE_STATUSLIVRO_LOCADO = StatusLivro.LOCADO;

    public static final Idioma DEFAULT_VALUE_IDIOMA_LIVRO = Idioma.PORTUGUES;

    public static final Status DEFAULT_VALUE_STATUS_ATIVA = Status.ATIVA;

    public static final Status DEFAULT_VALUE_STATUSLOCACAO_ATIVA = Status.ATIVA;

    public static final Status DEFAULT_VALUE_STATUSLOCACAO_CANCELADA = Status.CANCELADA;

    public static final Status DEFAULT_VALUE_STATUSLOCACAO_ATRASADA = Status.ATRASADA;

    public static final Status DEFAULT_VALUE_STATUSLOCACAO_FINALIZADA = Status.FINALIZADA;

    public static final Status DEFAULT_VALUE_STATUSRESERVA_ATIVA = Status.ATIVA;

    public static final Status DEFAULT_VALUE_STATUSRESERVA_FINALIZADA = Status.FINALIZADA;

    public static final Status DEFAULT_VALUE_STATUSRESERVA_CANCELADA = Status.CANCELADA;

    public static final int DEFAULT_VALUE_QUANTIDADE_LOCACAO = 1;

    public static final int DEFAULT_VALUE_QUANTIDADE_RENOVACAO_MAXIMA_LOCACAO = 3;

    public static final int DEFAULT_VALUE_QUANTIDADE_LOCACAO_INICIAL = 0;

    public static final String DEFAULT_VALUE_URL_PHOTOS_BOOK = "https://picsum.photos/id/";

    public static final String PHOTOS_BOOK_LENGTH_WIDTH_200_X_300 = "/200/300";

    public static final int DEFAULT_VALUE_DESCRESCENTAR_QUANTIDADE_LIVRO = 1;

    public static final int DEFAULT_VALUE_ACRESCENTAR_QUANTIDADE_LIVRO = 1;


    public static final String excecaoImplementacaoTarefaNaoEncontrada = "ClassNotFoundException: Exceção ao procurar implementação da Tarefa";

    public static final String trabalhoNaoEncontrado = "Tarefa Não localizada: ";

    public static final String excecaoNovoTrabalho = "SchedulerException: Exceção ao agendar o trabalho sob Nome: ";

    public static final String excecaoNovoTrabalhoCron = "SchedulerException: Exceção ao agendar o trabalho CRON sob Nome: ";

    public static final String excecaoAtualizarTrabalho = "Exception: Exceção na atualização do trabalho sob Nome: ";

    public static final String excecaoAtualizarTrabalhoCron = "Exception: Exceção na atualização do trabalho CRON sob Nome: ";

    public static final String excecaoCancelamentoTrabalho = "SchedulerException: Exceção no cancelamento do trabalho sob Nome: ";

    public static final String excecaoDeletarTrabalho = "SchedulerException: Exceção para deletar o trabalho sob Nome: ";

    public static final String excecaoPausarTrabalho = "SchedulerException: Exceção para Pausar o trabalho sob Nome: ";

    public static final String excecaoAoRetornarTrabalhoPausado = "SchedulerException: Exceção para Retornar o trabalho Pausado sob Nome: ";

    public static final String excecaoParaIniciarTrabalho = "SchedulerException: Exceção para Iniciar o trabalho sob Nome: ";

    public static final String excecaoParaChecarTrabalhoEmExcecucao = "SchedulerException: Exceção para checar trabalho em execucao sob Nome: ";

    public static final String excecaoParaChecarListaDeTrabalhos = "SchedulerException: Exceção para checar Lista de trabalhos. Mensagem: ";

    public static final String excecaoParaChecarSeOTrabalhoExiste = "SchedulerException: Exceção para checar se um determinado trabalho exsite. Nome do trabalho consultado: ";

    public static final String excecaoParaChecarStatusDoTrabalho = "SchedulerException: Exceção para checar o status de um determinado trabalho. Nome do trabalho consultado: ";

    public static final String excecaoParaPararUmTrabalho = "SchedulerException: Exceção para Parar um Trabalho. Nome do trabalho solicitado para Parar: ";


    private ConstantsUtils() {
    }
}
