package com.system.bibliotec.model;

import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.TipoCliente;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, TipoCliente> tipoCliente;
	public static volatile SingularAttribute<Cliente, Boolean> ativo;
	public static volatile SingularAttribute<Cliente, StatusCliente> statusCliente;
	public static volatile SingularAttribute<Cliente, Endereco> endereco;
	public static volatile SingularAttribute<Cliente, Genero> genero;
	public static volatile SingularAttribute<Cliente, String> cpf;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, LocalDate> dataNascimento;
	public static volatile SingularAttribute<Cliente, String> sobreNome;
	public static volatile SingularAttribute<Cliente, Contato> contato;

	public static final String TIPO_CLIENTE = "tipoCliente";
	public static final String ATIVO = "ativo";
	public static final String STATUS_CLIENTE = "statusCliente";
	public static final String ENDERECO = "endereco";
	public static final String GENERO = "genero";
	public static final String CPF = "cpf";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String SOBRE_NOME = "sobreNome";
	public static final String CONTATO = "contato";

}

