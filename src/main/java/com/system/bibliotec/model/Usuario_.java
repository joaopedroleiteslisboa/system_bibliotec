package com.system.bibliotec.model;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SetAttribute<Usuario, Permissao> permissoes;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile SingularAttribute<Usuario, TipoUsuarioVO> tipo;
	public static volatile SingularAttribute<Usuario, Instant> resetDate;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> chaveAtivacao;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Cliente> cliente;
	public static volatile SingularAttribute<Usuario, String> langKey;
	public static volatile SingularAttribute<Usuario, String> imageUrl;
	public static volatile SingularAttribute<Usuario, Funcionario> funcionario;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> chaveRenovacao;

	public static final String PERMISSOES = "permissoes";
	public static final String ATIVO = "ativo";
	public static final String TIPO = "tipo";
	public static final String RESET_DATE = "resetDate";
	public static final String NOME = "nome";
	public static final String CHAVE_ATIVACAO = "chaveAtivacao";
	public static final String SENHA = "senha";
	public static final String CLIENTE = "cliente";
	public static final String LANG_KEY = "langKey";
	public static final String IMAGE_URL = "imageUrl";
	public static final String FUNCIONARIO = "funcionario";
	public static final String EMAIL = "email";
	public static final String CHAVE_RENOVACAO = "chaveRenovacao";

}

