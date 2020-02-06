package com.system.bibliotec.model;

import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, Genero> genero;
	public static volatile SingularAttribute<Pessoa, String> cpf;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, LocalDate> dataNascimento;
	public static volatile SingularAttribute<Pessoa, String> sobreNome;
	public static volatile SingularAttribute<Pessoa, Contato> contato;

	public static final String ENDERECO = "endereco";
	public static final String GENERO = "genero";
	public static final String CPF = "cpf";
	public static final String NOME = "nome";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String SOBRE_NOME = "sobreNome";
	public static final String CONTATO = "contato";

}

