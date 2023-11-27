package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Categoria.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Categoria_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Categoria#livros
	 **/
	public static volatile ListAttribute<Categoria, Livro> livros;
	
	/**
	 * @see com.system.bibliotec.model.Categoria#nome
	 **/
	public static volatile SingularAttribute<Categoria, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Categoria
	 **/
	public static volatile EntityType<Categoria> class_;

	public static final String LIVROS = "livros";
	public static final String NOME = "nome";

}

