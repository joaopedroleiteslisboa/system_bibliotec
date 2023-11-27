package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.StatusProcessamento;
import com.system.bibliotec.model.enums.TipoSolicitacao;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@StaticMetamodel(Solicitacoes.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Solicitacoes_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#dataSolicitacao
	 **/
	public static volatile SingularAttribute<Solicitacoes, Instant> dataSolicitacao;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#tipo
	 **/
	public static volatile SingularAttribute<Solicitacoes, TipoSolicitacao> tipo;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#horaRetiradaExemplar
	 **/
	public static volatile SingularAttribute<Solicitacoes, LocalTime> horaRetiradaExemplar;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#idExemplar
	 **/
	public static volatile SingularAttribute<Solicitacoes, Long> idExemplar;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#dataRetiradaExemplar
	 **/
	public static volatile SingularAttribute<Solicitacoes, LocalDate> dataRetiradaExemplar;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#statusProcessamento
	 **/
	public static volatile SingularAttribute<Solicitacoes, StatusProcessamento> statusProcessamento;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#rejeitado
	 **/
	public static volatile SingularAttribute<Solicitacoes, Boolean> rejeitado;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#usuario
	 **/
	public static volatile SingularAttribute<Solicitacoes, Usuario> usuario;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#horaSolicitacao
	 **/
	public static volatile SingularAttribute<Solicitacoes, Instant> horaSolicitacao;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes
	 **/
	public static volatile EntityType<Solicitacoes> class_;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#status
	 **/
	public static volatile SingularAttribute<Solicitacoes, Status> status;
	
	/**
	 * @see com.system.bibliotec.model.Solicitacoes#descricao
	 **/
	public static volatile SingularAttribute<Solicitacoes, String> descricao;

	public static final String DATA_SOLICITACAO = "dataSolicitacao";
	public static final String TIPO = "tipo";
	public static final String HORA_RETIRADA_EXEMPLAR = "horaRetiradaExemplar";
	public static final String ID_EXEMPLAR = "idExemplar";
	public static final String DATA_RETIRADA_EXEMPLAR = "dataRetiradaExemplar";
	public static final String STATUS_PROCESSAMENTO = "statusProcessamento";
	public static final String REJEITADO = "rejeitado";
	public static final String USUARIO = "usuario";
	public static final String HORA_SOLICITACAO = "horaSolicitacao";
	public static final String STATUS = "status";
	public static final String DESCRICAO = "descricao";

}

