package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Idioma;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDate;

@StaticMetamodel(Livro.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Livro_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Livro#categorias
	 **/
	public static volatile SetAttribute<Livro, Categoria> categorias;
	
	/**
	 * @see com.system.bibliotec.model.Livro#dataPublicacao
	 **/
	public static volatile SingularAttribute<Livro, LocalDate> dataPublicacao;
	
	/**
	 * @see com.system.bibliotec.model.Livro#nome
	 **/
	public static volatile SingularAttribute<Livro, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Livro#idioma
	 **/
	public static volatile SingularAttribute<Livro, Idioma> idioma;
	
	/**
	 * @see com.system.bibliotec.model.Livro#autores
	 **/
	public static volatile SetAttribute<Livro, Autor> autores;
	
	/**
	 * @see com.system.bibliotec.model.Livro#editora
	 **/
	public static volatile SingularAttribute<Livro, Editora> editora;
	
	/**
	 * @see com.system.bibliotec.model.Livro#edicao
	 **/
	public static volatile SingularAttribute<Livro, String> edicao;
	
	/**
	 * @see com.system.bibliotec.model.Livro#imagenUrl
	 **/
	public static volatile SingularAttribute<Livro, String> imagenUrl;
	
	/**
	 * @see com.system.bibliotec.model.Livro#descricao
	 **/
	public static volatile SingularAttribute<Livro, String> descricao;
	
	/**
	 * @see com.system.bibliotec.model.Livro#valorUnitario
	 **/
	public static volatile SingularAttribute<Livro, BigDecimal> valorUnitario;
	
	/**
	 * @see com.system.bibliotec.model.Livro#isbn13
	 **/
	public static volatile SingularAttribute<Livro, String> isbn13;
	
	/**
	 * @see com.system.bibliotec.model.Livro#numeroPaginas
	 **/
	public static volatile SingularAttribute<Livro, Integer> numeroPaginas;
	
	/**
	 * @see com.system.bibliotec.model.Livro
	 **/
	public static volatile EntityType<Livro> class_;
	
	/**
	 * @see com.system.bibliotec.model.Livro#codBarras
	 **/
	public static volatile SingularAttribute<Livro, String> codBarras;
	
	/**
	 * @see com.system.bibliotec.model.Livro#quantidade
	 **/
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

