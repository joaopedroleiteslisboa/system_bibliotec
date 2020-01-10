package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.StatusLocacao;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Locacao.class)
public abstract class Locacao_ {

	public static volatile SingularAttribute<Locacao, Integer> quantidadeDeRenovacao;
	public static volatile SingularAttribute<Locacao, LocalDate> dataLocacao;
	public static volatile SingularAttribute<Locacao, StatusLocacao> statusLocacao;
	public static volatile SingularAttribute<Locacao, String> observacoesEntrega;
	public static volatile SingularAttribute<Locacao, LocalTime> horaCancelamento;
	public static volatile SingularAttribute<Locacao, String> observacoesDevolucao;
	public static volatile SingularAttribute<Locacao, LocalTime> horaLocacao;
	public static volatile SingularAttribute<Locacao, LocalTime> horaEncerramento;
	public static volatile SingularAttribute<Locacao, LocalDate> dataPrevisaoTerminoLocacao;
	public static volatile SingularAttribute<Locacao, LocalDate> dataCancelamento;
	public static volatile SingularAttribute<Locacao, Cliente> cliente;
	public static volatile SingularAttribute<Locacao, LocalDate> dataEncerramento;
	public static volatile SingularAttribute<Locacao, Livro> livro;
	public static volatile SingularAttribute<Locacao, Long> id;

	public static final String QUANTIDADE_DE_RENOVACAO = "quantidadeDeRenovacao";
	public static final String DATA_LOCACAO = "dataLocacao";
	public static final String STATUS_LOCACAO = "statusLocacao";
	public static final String OBSERVACOES_ENTREGA = "observacoesEntrega";
	public static final String HORA_CANCELAMENTO = "horaCancelamento";
	public static final String OBSERVACOES_DEVOLUCAO = "observacoesDevolucao";
	public static final String HORA_LOCACAO = "horaLocacao";
	public static final String HORA_ENCERRAMENTO = "horaEncerramento";
	public static final String DATA_PREVISAO_TERMINO_LOCACAO = "dataPrevisaoTerminoLocacao";
	public static final String DATA_CANCELAMENTO = "dataCancelamento";
	public static final String CLIENTE = "cliente";
	public static final String DATA_ENCERRAMENTO = "dataEncerramento";
	public static final String LIVRO = "livro";
	public static final String ID = "id";

}

