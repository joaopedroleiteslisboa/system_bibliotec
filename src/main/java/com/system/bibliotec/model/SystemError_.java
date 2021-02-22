package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.TipoErrorSistema;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemError.class)
public abstract class SystemError_ {

	public static volatile SingularAttribute<SystemError, String> idUser;
	public static volatile SingularAttribute<SystemError, String> usuarioError;
	public static volatile SingularAttribute<SystemError, TipoErrorSistema> tipo;
	public static volatile SingularAttribute<SystemError, String> metodoError;
	public static volatile SingularAttribute<SystemError, Boolean> resolvido;
	public static volatile SingularAttribute<SystemError, String> classs;
	public static volatile SingularAttribute<SystemError, String> operacao;
	public static volatile SingularAttribute<SystemError, Long> id;
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

