package com.system.bibliotec.model.embeddeds;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Contato.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Contato_ {

	
	/**
	 * @see com.system.bibliotec.model.embeddeds.Contato#telefoneResidencial
	 **/
	public static volatile SingularAttribute<Contato, String> telefoneResidencial;
	
	/**
	 * @see com.system.bibliotec.model.embeddeds.Contato#email_2
	 **/
	public static volatile SingularAttribute<Contato, String> email_2;
	
	/**
	 * @see com.system.bibliotec.model.embeddeds.Contato#email_1
	 **/
	public static volatile SingularAttribute<Contato, String> email_1;
	
	/**
	 * @see com.system.bibliotec.model.embeddeds.Contato#celular
	 **/
	public static volatile SingularAttribute<Contato, String> celular;
	
	/**
	 * @see com.system.bibliotec.model.embeddeds.Contato
	 **/
	public static volatile EmbeddableType<Contato> class_;

	public static final String TELEFONE_RESIDENCIAL = "telefoneResidencial";
	public static final String EMAIL_2 = "email_2";
	public static final String EMAIL_1 = "email_1";
	public static final String CELULAR = "celular";

}

