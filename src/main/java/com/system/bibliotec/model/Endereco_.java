package com.system.bibliotec.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Endereco.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Endereco_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Endereco#uf
	 **/
	public static volatile SingularAttribute<Endereco, String> uf;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#cidade
	 **/
	public static volatile SingularAttribute<Endereco, String> cidade;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#complemento
	 **/
	public static volatile SingularAttribute<Endereco, String> complemento;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#numero
	 **/
	public static volatile SingularAttribute<Endereco, String> numero;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#logradouro
	 **/
	public static volatile SingularAttribute<Endereco, String> logradouro;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#bairro
	 **/
	public static volatile SingularAttribute<Endereco, String> bairro;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#ibge
	 **/
	public static volatile SingularAttribute<Endereco, String> ibge;
	
	/**
	 * @see com.system.bibliotec.model.Endereco
	 **/
	public static volatile EntityType<Endereco> class_;
	
	/**
	 * @see com.system.bibliotec.model.Endereco#cep
	 **/
	public static volatile SingularAttribute<Endereco, String> cep;

	public static final String UF = "uf";
	public static final String CIDADE = "cidade";
	public static final String COMPLEMENTO = "complemento";
	public static final String NUMERO = "numero";
	public static final String LOGRADOURO = "logradouro";
	public static final String BAIRRO = "bairro";
	public static final String IBGE = "ibge";
	public static final String CEP = "cep";

}

