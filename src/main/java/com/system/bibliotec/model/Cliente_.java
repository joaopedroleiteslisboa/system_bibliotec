package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.TipoCliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends com.system.bibliotec.model.Pessoa_ {

	public static volatile SingularAttribute<Cliente, TipoCliente> tipoCliente;
	public static volatile SingularAttribute<Cliente, Boolean> ativo;
	public static volatile SingularAttribute<Cliente, StatusCliente> statusCliente;

	public static final String TIPO_CLIENTE = "tipoCliente";
	public static final String ATIVO = "ativo";
	public static final String STATUS_CLIENTE = "statusCliente";

}

