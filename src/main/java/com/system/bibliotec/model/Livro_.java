package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Idioma;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livro.class)
public abstract class Livro_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SetAttribute<Livro, Categoria> categorias;
	public static volatile SingularAttribute<Livro, LocalDate> dataPublicacao;
	public static volatile SingularAttribute<Livro, String> nome;
	public static volatile SingularAttribute<Livro, Idioma> idioma;
	public static volatile SetAttribute<Livro, Autor> autores;
	public static volatile SingularAttribute<Livro, Editora> editora;
	public static volatile SingularAttribute<Livro, String> edicao;
	public static volatile SingularAttribute<Livro, String> imagenUrl;
	public static volatile SingularAttribute<Livro, String> descricao;
	public static volatile SingularAttribute<Livro, BigDecimal> valorUnitario;
	public static volatile SingularAttribute<Livro, String> isbn13;
	public static volatile SingularAttribute<Livro, Integer> numeroPaginas;
	public static volatile SingularAttribute<Livro, String> codBarras;
	public static volatile SingularAttribute<Livro, Integer> quantidade;

	public static final String CATEGORIAS = "categorias";
	public static final String DATA_PUBLICACAO = "dataPublicacao";
	public static final String NOME = "nome";
	public static final String IDIOMA = "idioma";
	public static final String AUTORES = "autores";
	public static final String EDITORA = "editora";
	public static final String EDICAO = "edicao";
	public static final String IMAGEN_URL = "imagenUrl";
	public static final String DESCRICAO = "descricao";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String ISBN13 = "isbn13";
	public static final String NUMERO_PAGINAS = "numeroPaginas";
	public static final String COD_BARRAS = "codBarras";
	public static final String QUANTIDADE = "quantidade";

}

