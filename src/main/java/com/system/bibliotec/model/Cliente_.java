package com.system.bibliotec.model;

import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.TipoCliente;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, TipoCliente> tipoCliente;
	public static volatile SingularAttribute<Cliente, StatusCliente> statusCliente;
	public static volatile SingularAttribute<Cliente, Endereco> idEndereco;
	public static volatile SingularAttribute<Cliente, Genero> genero;
	public static volatile SingularAttribute<Cliente, String> cpf;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, LocalDate> dataNascimento;
	public static volatile SingularAttribute<Cliente, String> sobreNome;
	public static volatile SingularAttribute<Cliente, Contato> contato;

}

