package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TipoUsuarioVO.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class TipoUsuarioVO_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.TipoUsuarioVO#tipo
	 **/
	public static volatile SingularAttribute<TipoUsuarioVO, String> tipo;
	
	/**
	 * @see com.system.bibliotec.model.TipoUsuarioVO
	 **/
	public static volatile EntityType<TipoUsuarioVO> class_;

	public static final String TIPO = "tipo";

}

