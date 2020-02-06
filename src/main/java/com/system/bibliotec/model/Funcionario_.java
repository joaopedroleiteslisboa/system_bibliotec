package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.StatusFuncionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ extends com.system.bibliotec.model.Pessoa_ {

	public static volatile SingularAttribute<Funcionario, Boolean> ativo;
	public static volatile SingularAttribute<Funcionario, String> matricula;
	public static volatile SingularAttribute<Funcionario, StatusFuncionario> statusFuncionario;
	public static volatile SingularAttribute<Funcionario, Cargo> cargo;

	public static final String ATIVO = "ativo";
	public static final String MATRICULA = "matricula";
	public static final String STATUS_FUNCIONARIO = "statusFuncionario";
	public static final String CARGO = "cargo";

}

