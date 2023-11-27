package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalTime;

@StaticMetamodel(Reservas.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Reservas_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	
	/**
	 * @see com.system.bibliotec.model.Reservas#horaRetiradaLivro
	 **/
	public static volatile SingularAttribute<Reservas, LocalTime> horaRetiradaLivro;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#dataReserva
	 **/
	public static volatile SingularAttribute<Reservas, LocalDate> dataReserva;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#livro
	 **/
	public static volatile SingularAttribute<Reservas, Livro> livro;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#horaReserva
	 **/
	public static volatile SingularAttribute<Reservas, LocalTime> horaReserva;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#usuario
	 **/
	public static volatile SingularAttribute<Reservas, Usuario> usuario;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#dataPrevisaoTermino
	 **/
	public static volatile SingularAttribute<Reservas, LocalDate> dataPrevisaoTermino;
	
	/**
	 * @see com.system.bibliotec.model.Reservas
	 **/
	public static volatile EntityType<Reservas> class_;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#dataRetiradaLivro
	 **/
	public static volatile SingularAttribute<Reservas, LocalDate> dataRetiradaLivro;
	
	/**
	 * @see com.system.bibliotec.model.Reservas#status
	 **/
	public static volatile SingularAttribute<Reservas, Status> status;

	public static final String HORA_RETIRADA_LIVRO = "horaRetiradaLivro";
	public static final String DATA_RESERVA = "dataReserva";
	public static final String LIVRO = "livro";
	public static final String HORA_RESERVA = "horaReserva";
	public static final String USUARIO = "usuario";
	public static final String DATA_PREVISAO_TERMINO = "dataPrevisaoTermino";
	public static final String DATA_RETIRADA_LIVRO = "dataRetiradaLivro";
	public static final String STATUS = "status";

}

