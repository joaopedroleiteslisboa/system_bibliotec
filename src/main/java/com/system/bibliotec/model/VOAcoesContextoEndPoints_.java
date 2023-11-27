package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VOAcoesContextoEndPoints.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class VOAcoesContextoEndPoints_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#dataHeaders
	 **/
	public static volatile MapAttribute<VOAcoesContextoEndPoints, String, String> dataHeaders;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#method
	 **/
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> method;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#ip
	 **/
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> ip;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#body
	 **/
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> body;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#atividadeEsperada
	 **/
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> atividadeEsperada;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints
	 **/
	public static volatile EntityType<VOAcoesContextoEndPoints> class_;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#dataParans
	 **/
	public static volatile MapAttribute<VOAcoesContextoEndPoints, String, String> dataParans;
	
	/**
	 * @see com.system.bibliotec.model.VOAcoesContextoEndPoints#recursoSolicitado
	 **/
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> recursoSolicitado;

	public static final String DATA_HEADERS = "dataHeaders";
	public static final String METHOD = "method";
	public static final String IP = "ip";
	public static final String BODY = "body";
	public static final String ATIVIDADE_ESPERADA = "atividadeEsperada";
	public static final String DATA_PARANS = "dataParans";
	public static final String RECURSO_SOLICITADO = "recursoSolicitado";

}

