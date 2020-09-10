package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.TipoSolicitacao;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Solicitacoes.class)
public abstract class Solicitacoes_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Solicitacoes, Instant> dataSolicitacao;
	public static volatile SingularAttribute<Solicitacoes, TipoSolicitacao> tipo;
	public static volatile SingularAttribute<Solicitacoes, Long> idCliente;
	public static volatile SingularAttribute<Solicitacoes, LocalTime> horaRetiradaExemplar;
	public static volatile SingularAttribute<Solicitacoes, Long> idExemplar;
	public static volatile SingularAttribute<Solicitacoes, LocalDate> dataRetiradaExemplar;
	public static volatile SingularAttribute<Solicitacoes, Boolean> rejeitado;
	public static volatile SingularAttribute<Solicitacoes, Status> status;
	public static volatile SingularAttribute<Solicitacoes, String> descricao;

	public static final String DATA_SOLICITACAO = "dataSolicitacao";
	public static final String TIPO = "tipo";
	public static final String ID_CLIENTE = "idCliente";
	public static final String HORA_RETIRADA_EXEMPLAR = "horaRetiradaExemplar";
	public static final String ID_EXEMPLAR = "idExemplar";
	public static final String DATA_RETIRADA_EXEMPLAR = "dataRetiradaExemplar";
	public static final String REJEITADO = "rejeitado";
	public static final String STATUS = "status";
	public static final String DESCRICAO = "descricao";

}

