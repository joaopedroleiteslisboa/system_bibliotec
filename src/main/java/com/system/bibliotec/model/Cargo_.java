package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Cargo.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Cargo_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Cargo#codigo
	 **/
	public static volatile SingularAttribute<Cargo, String> codigo;
	
	/**
	 * @see com.system.bibliotec.model.Cargo#nome
	 **/
	public static volatile SingularAttribute<Cargo, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Cargo
	 **/
	public static volatile EntityType<Cargo> class_;

	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";

}

