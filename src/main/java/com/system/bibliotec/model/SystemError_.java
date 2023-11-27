package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.TipoErrorSistema;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SystemError.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class SystemError_ {

	
	/**
	 * @see com.system.bibliotec.model.SystemError#idUser
	 **/
	public static volatile SingularAttribute<SystemError, String> idUser;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#usuarioError
	 **/
	public static volatile SingularAttribute<SystemError, String> usuarioError;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#tipo
	 **/
	public static volatile SingularAttribute<SystemError, TipoErrorSistema> tipo;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#metodoError
	 **/
	public static volatile SingularAttribute<SystemError, String> metodoError;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#resolvido
	 **/
	public static volatile SingularAttribute<SystemError, Boolean> resolvido;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#classs
	 **/
	public static volatile SingularAttribute<SystemError, String> classs;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#operacao
	 **/
	public static volatile SingularAttribute<SystemError, String> operacao;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#id
	 **/
	public static volatile SingularAttribute<SystemError, Long> id;
	
	/**
	 * @see com.system.bibliotec.model.SystemError
	 **/
	public static volatile EntityType<SystemError> class_;
	
	/**
	 * @see com.system.bibliotec.model.SystemError#descricao
	 **/
	public static volatile SingularAttribute<SystemError, String> descricao;

	public static final String ID_USER = "idUser";
	public static final String USUARIO_ERROR = "usuarioError";
	public static final String TIPO = "tipo";
	public static final String METODO_ERROR = "metodoError";
	public static final String RESOLVIDO = "resolvido";
	public static final String CLASSS = "classs";
	public static final String OPERACAO = "operacao";
	public static final String ID = "id";
	public static final String DESCRICAO = "descricao";

}

