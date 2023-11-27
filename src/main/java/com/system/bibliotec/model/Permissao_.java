package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permissao.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Permissao_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Permissao
	 **/
	public static volatile EntityType<Permissao> class_;
	
	/**
	 * @see com.system.bibliotec.model.Permissao#descricao
	 **/
	public static volatile SingularAttribute<Permissao, String> descricao;

	public static final String DESCRICAO = "descricao";

}

