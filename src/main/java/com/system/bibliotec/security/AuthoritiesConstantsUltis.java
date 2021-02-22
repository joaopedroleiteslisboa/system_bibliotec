package com.system.bibliotec.security;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AuthoritiesConstantsUltis {

    private static HashMap<String, String> rolesExemplos;

    public static final String ROLE_CADASTRAR_LIVRO = "ROLE_CADASTRAR_LIVRO";
    public static final String ROLE_PESQUISAR_LIVRO = "ROLE_PESQUISAR_LIVRO";
    public static final String ROLE_REMOVER_LIVRO = "ROLE_REMOVER_LIVRO";

    public static final String ROLE_CADASTRAR_PESSOA = "ROLE_CADASTRAR_PESSOA";
    public static final String ROLE_REMOVER_PESSOA = "ROLE_REMOVER_PESSOA";
    public static final String ROLE_PESQUISAR_PESSOA = "ROLE_PESQUISAR_PESSOA";

    public static final String ROLE_CADASTRAR_LOCACAO = "ROLE_CADASTRAR_LOCACAO";
    public static final String ROLE_REMOVER_LOCACAO = "ROLE_REMOVER_LOCACAO";
    public static final String ROLE_PESQUISAR_LOCACAO = "ROLE_PESQUISAR_LOCACAO";

    public static final String ROLE_CADASTRAR_RESERVA = "ROLE_CADASTRAR_RESERVA";
    public static final String ROLE_REMOVER_RESERVA = "ROLE_REMOVER_RESERVA";
    public static final String ROLE_PESQUISAR_RESERVA = "ROLE_PESQUISAR_RESERVA";

    public static final String ROLE_CADASTRAR_CLIENT_APP = "ROLE_CADASTRAR_CLIENT_APP";
    public static final String ROLE_REMOVER_CLIENT_APP = "ROLE_REMOVER_CLIENT_APP";
    public static final String ROLE_PESQUISAR_CLIENT_APP = "ROLE_PESQUISAR_CLIENT_APP";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER_ANONIMO = "ROLE_USER_ANONIMO";
    public static final String ROLE_USER_SYSTEM = "ROLE_USER_SYSTEM";


}
