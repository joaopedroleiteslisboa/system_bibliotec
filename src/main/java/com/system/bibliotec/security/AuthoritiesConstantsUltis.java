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

	static {

		rolesExemplos = new HashMap<String, String>(1000);

		addRoles();

	}

	private static void addRoles() {
		// TODO Auto-generated method stub

		Class<?> classs = AuthoritiesConstantsUltis.class.getClass();

		for (Field f : classs.getFields()) {

			f.setAccessible(true);
			if (!f.getName().equalsIgnoreCase("rolesExemplos")) {

				try {
					rolesExemplos.put(f.getName().toString().toLowerCase(),
							f.get(f.getName()).toString().toLowerCase());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
