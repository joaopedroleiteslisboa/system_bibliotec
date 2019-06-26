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

	public static volatile SingularAttribute<Locacao, LocalDate> dataCancelamentoLocacao;
	public static volatile SingularAttribute<Locacao, LocalTime> horaCancelamentoLocacao;
	public static volatile SingularAttribute<Locacao, Integer> quantidadeDeRenovacao;
	public static volatile SingularAttribute<Locacao, LocalDate> dataLocacao;
	public static volatile SingularAttribute<Locacao, Cliente> idCliente;
	public static volatile SingularAttribute<Locacao, StatusLocacao> statusLocacao;
	public static volatile SingularAttribute<Locacao, Livro> idLivro;
	public static volatile SingularAttribute<Locacao, String> observacoesDeEntrega;
	public static volatile SingularAttribute<Locacao, LocalTime> horaLocacao;
	public static volatile SingularAttribute<Locacao, LocalDate> dataTerminoLocacao;
	public static volatile SingularAttribute<Locacao, Long> id;
	public static volatile SingularAttribute<Locacao, String> observacoesDeDevolucao;

}

