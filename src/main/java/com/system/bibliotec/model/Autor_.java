package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Autor.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Autor_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Autor#livros
	 **/
	public static volatile ListAttribute<Autor, Livro> livros;
	
	/**
	 * @see com.system.bibliotec.model.Autor#nome
	 **/
	public static volatile SingularAttribute<Autor, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Autor
	 **/
	public static volatile EntityType<Autor> class_;
	
	/**
	 * @see com.system.bibliotec.model.Autor#descricao
	 **/
	public static volatile SingularAttribute<Autor, String> descricao;

	public static final String LIVROS = "livros";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";

}

