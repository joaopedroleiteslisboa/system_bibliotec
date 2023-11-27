package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Editora.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Editora_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Editora#livros
	 **/
	public static volatile ListAttribute<Editora, Livro> livros;
	
	/**
	 * @see com.system.bibliotec.model.Editora#nome
	 **/
	public static volatile SingularAttribute<Editora, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Editora
	 **/
	public static volatile EntityType<Editora> class_;
	
	/**
	 * @see com.system.bibliotec.model.Editora#descricao
	 **/
	public static volatile SingularAttribute<Editora, String> descricao;

	public static final String LIVROS = "livros";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";

}

