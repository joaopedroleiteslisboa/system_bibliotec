package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Idioma;
import com.system.bibliotec.model.enums.StatusLivro;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livro.class)
public abstract class Livro_ {

	public static volatile SetAttribute<Livro, Categoria> categorias;
	public static volatile SingularAttribute<Livro, LocalDate> dataPublicacao;
	public static volatile SingularAttribute<Livro, String> nome;
	public static volatile SingularAttribute<Livro, Idioma> idioma;
	public static volatile SetAttribute<Livro, Autor> autores;
	public static volatile SingularAttribute<Livro, String> edicao;
	public static volatile SingularAttribute<Livro, Editora> idEditora;
	public static volatile SingularAttribute<Livro, String> descricao;
	public static volatile SingularAttribute<Livro, BigDecimal> valorUnitario;
	public static volatile SingularAttribute<Livro, StatusLivro> statusLivro;
	public static volatile SingularAttribute<Livro, String> isbn13;
	public static volatile SingularAttribute<Livro, Integer> numeroPaginas;
	public static volatile SingularAttribute<Livro, Long> id;
	public static volatile SingularAttribute<Livro, String> codBarras;
	public static volatile SingularAttribute<Livro, Integer> quantidade;

}

