package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Locacoes.class)
public abstract class Locacoes_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Locacoes, Integer> quantidadeDeRenovacao;
	public static volatile SingularAttribute<Locacoes, LocalDate> dataLocacao;
	public static volatile SingularAttribute<Locacoes, String> observacoesEntrega;
	public static volatile SingularAttribute<Locacoes, LocalTime> horaCancelamento;
	public static volatile SingularAttribute<Locacoes, String> observacoesDevolucao;
	public static volatile SingularAttribute<Locacoes, LocalTime> horaLocacao;
	public static volatile SingularAttribute<Locacoes, LocalTime> horaEncerramento;
	public static volatile SingularAttribute<Locacoes, LocalDate> dataCancelamento;
	public static volatile SingularAttribute<Locacoes, LocalDate> dataEncerramento;
	public static volatile SingularAttribute<Locacoes, Livro> livro;
	public static volatile SingularAttribute<Locacoes, Usuario> usuario;
	public static volatile SingularAttribute<Locacoes, LocalDate> dataPrevisaoTermino;
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

