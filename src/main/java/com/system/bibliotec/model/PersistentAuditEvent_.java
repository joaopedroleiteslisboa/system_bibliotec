package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(PersistentAuditEvent.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class PersistentAuditEvent_ {

	
	/**
	 * @see com.system.bibliotec.model.PersistentAuditEvent#principal
	 **/
	public static volatile SingularAttribute<PersistentAuditEvent, String> principal;
	
	/**
	 * @see com.system.bibliotec.model.PersistentAuditEvent#auditEventDate
	 **/
	public static volatile SingularAttribute<PersistentAuditEvent, Instant> auditEventDate;
	
	/**
	 * @see com.system.bibliotec.model.PersistentAuditEvent#data
	 **/
	public static volatile MapAttribute<PersistentAuditEvent, String, String> data;
	
	/**
	 * @see com.system.bibliotec.model.PersistentAuditEvent#id
	 **/
	public static volatile SingularAttribute<PersistentAuditEvent, Long> id;
	
	/**
	 * @see com.system.bibliotec.model.PersistentAuditEvent
	 **/
	public static volatile EntityType<PersistentAuditEvent> class_;
	
	/**
	 * @see com.system.bibliotec.model.PersistentAuditEvent#auditEventType
	 **/
	public static volatile SingularAttribute<PersistentAuditEvent, String> auditEventType;

	public static final String PRINCIPAL = "principal";
	public static final String AUDIT_EVENT_DATE = "auditEventDate";
	public static final String DATA = "data";
	public static final String ID = "id";
	public static final String AUDIT_EVENT_TYPE = "auditEventType";

}

