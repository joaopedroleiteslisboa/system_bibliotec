package com.system.bibliotec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile ListAttribute<Categoria, Livro> livros;
	public static volatile SingularAttribute<Categoria, String> nome;
	public static volatile SingularAttribute<Categoria, Long> id;

	public static final String LIVROS = "livros";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

