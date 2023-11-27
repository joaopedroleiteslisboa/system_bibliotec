package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Funcionario.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Funcionario_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Funcionario#ativo
	 **/
	public static volatile SingularAttribute<Funcionario, Boolean> ativo;
	
	/**
	 * @see com.system.bibliotec.model.Funcionario#matricula
	 **/
	public static volatile SingularAttribute<Funcionario, String> matricula;
	
	/**
	 * @see com.system.bibliotec.model.Funcionario#cargo
	 **/
	public static volatile SingularAttribute<Funcionario, Cargo> cargo;
	
	/**
	 * @see com.system.bibliotec.model.Funcionario
	 **/
	public static volatile EntityType<Funcionario> class_;

	public static final String ATIVO = "ativo";
	public static final String MATRICULA = "matricula";
	public static final String CARGO = "cargo";

}

