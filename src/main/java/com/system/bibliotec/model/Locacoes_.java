package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalTime;

@StaticMetamodel(Locacoes.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Locacoes_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Locacoes#quantidadeDeRenovacao
	 **/
	public static volatile SingularAttribute<Locacoes, Integer> quantidadeDeRenovacao;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#dataLocacao
	 **/
	public static volatile SingularAttribute<Locacoes, LocalDate> dataLocacao;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#observacoesEntrega
	 **/
	public static volatile SingularAttribute<Locacoes, String> observacoesEntrega;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#horaCancelamento
	 **/
	public static volatile SingularAttribute<Locacoes, LocalTime> horaCancelamento;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#observacoesDevolucao
	 **/
	public static volatile SingularAttribute<Locacoes, String> observacoesDevolucao;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#horaLocacao
	 **/
	public static volatile SingularAttribute<Locacoes, LocalTime> horaLocacao;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#horaEncerramento
	 **/
	public static volatile SingularAttribute<Locacoes, LocalTime> horaEncerramento;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#dataCancelamento
	 **/
	public static volatile SingularAttribute<Locacoes, LocalDate> dataCancelamento;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#dataEncerramento
	 **/
	public static volatile SingularAttribute<Locacoes, LocalDate> dataEncerramento;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#livro
	 **/
	public static volatile SingularAttribute<Locacoes, Livro> livro;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#usuario
	 **/
	public static volatile SingularAttribute<Locacoes, Usuario> usuario;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#dataPrevisaoTermino
	 **/
	public static volatile SingularAttribute<Locacoes, LocalDate> dataPrevisaoTermino;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes
	 **/
	public static volatile EntityType<Locacoes> class_;
	
	/**
	 * @see com.system.bibliotec.model.Locacoes#status
	 **/
	public static volatile SingularAttribute<Locacoes, Status> status;

	public static final String QUANTIDADE_DE_RENOVACAO = "quantidadeDeRenovacao";
	public static final String DATA_LOCACAO = "dataLocacao";
	public static final String OBSERVACOES_ENTREGA = "observacoesEntrega";
	public static final String HORA_CANCELAMENTO = "horaCancelamento";
	public static final String OBSERVACOES_DEVOLUCAO = "observacoesDevolucao";
	public static final String HORA_LOCACAO = "horaLocacao";
	public static final String HORA_ENCERRAMENTO = "horaEncerramento";
	public static final String DATA_CANCELAMENTO = "dataCancelamento";
	public static final String DATA_ENCERRAMENTO = "dataEncerramento";
	public static final String LIVRO = "livro";
	public static final String USUARIO = "usuario";
	public static final String DATA_PREVISAO_TERMINO = "dataPrevisaoTermino";
	public static final String STATUS = "status";

}

