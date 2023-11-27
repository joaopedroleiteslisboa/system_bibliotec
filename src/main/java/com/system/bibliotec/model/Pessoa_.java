package com.system.bibliotec.model;

import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusPessoa;
import com.system.bibliotec.model.enums.TipoPessoa;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Pessoa.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Pessoa_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Pessoa#ativo
	 **/
	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#tipoPessoa
	 **/
	public static volatile SingularAttribute<Pessoa, TipoPessoa> tipoPessoa;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#endereco
	 **/
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#genero
	 **/
	public static volatile SingularAttribute<Pessoa, Genero> genero;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#cpf
	 **/
	public static volatile SingularAttribute<Pessoa, String> cpf;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#nome
	 **/
	public static volatile SingularAttribute<Pessoa, String> nome;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#statusPessoa
	 **/
	public static volatile SingularAttribute<Pessoa, StatusPessoa> statusPessoa;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#cnpj
	 **/
	public static volatile SingularAttribute<Pessoa, String> cnpj;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#dataNascimento
	 **/
	public static volatile SingularAttribute<Pessoa, LocalDate> dataNascimento;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa
	 **/
	public static volatile MappedSuperclassType<Pessoa> class_;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#sobreNome
	 **/
	public static volatile SingularAttribute<Pessoa, String> sobreNome;
	
	/**
	 * @see com.system.bibliotec.model.Pessoa#contato
	 **/
	public static volatile SingularAttribute<Pessoa, Contato> contato;

	public static final String ATIVO = "ativo";
	public static final String TIPO_PESSOA = "tipoPessoa";
	public static final String ENDERECO = "endereco";
	public static final String GENERO = "genero";
	public static final String CPF = "cpf";
	public static final String NOME = "nome";
	public static final String STATUS_PESSOA = "statusPessoa";
	public static final String CNPJ = "cnpj";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String SOBRE_NOME = "sobreNome";
	public static final String CONTATO = "contato";

}

