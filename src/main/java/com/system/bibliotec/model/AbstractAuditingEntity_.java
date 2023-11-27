package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(AbstractAuditingEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.AbstractAuditingEntity#createdDate
	 **/
	public static volatile SingularAttribute<AbstractAuditingEntity, Instant> createdDate;
	
	/**
	 * @see com.system.bibliotec.model.AbstractAuditingEntity#createdBy
	 **/
	public static volatile SingularAttribute<AbstractAuditingEntity, String> createdBy;
	
	/**
	 * @see com.system.bibliotec.model.AbstractAuditingEntity#lastModifiedDate
	 **/
	public static volatile SingularAttribute<AbstractAuditingEntity, Instant> lastModifiedDate;
	
	/**
	 * @see com.system.bibliotec.model.AbstractAuditingEntity#lastModifiedBy
	 **/
	public static volatile SingularAttribute<AbstractAuditingEntity, String> lastModifiedBy;
	
	/**
	 * @see com.system.bibliotec.model.AbstractAuditingEntity#id
	 **/
	public static volatile SingularAttribute<AbstractAuditingEntity, Long> id;
	
	/**
	 * @see com.system.bibliotec.model.AbstractAuditingEntity
	 **/
	public static volatile MappedSuperclassType<AbstractAuditingEntity> class_;

	public static final String CREATED_DATE = "createdDate";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";
	public static final String ID = "id";

}

