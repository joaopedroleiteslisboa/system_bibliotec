package com.system.bibliotec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VOAcoesContextoEndPoints.class)
public abstract class VOAcoesContextoEndPoints_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile MapAttribute<VOAcoesContextoEndPoints, String, String> dataHeaders;
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> method;
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> ip;
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> body;
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> atividadeEsperada;
	public static volatile MapAttribute<VOAcoesContextoEndPoints, String, String> dataParans;
	public static volatile SingularAttribute<VOAcoesContextoEndPoints, String> recursoSolicitado;

	public static final String DATA_HEADERS = "dataHeaders";
	public static final String METHOD = "method";
	public static final String IP = "ip";
	public static final String BODY = "body";
	public static final String ATIVIDADE_ESPERADA = "atividadeEsperada";
	public static final String DATA_PARANS = "dataParans";
	public static final String RECURSO_SOLICITADO = "recursoSolicitado";

}

