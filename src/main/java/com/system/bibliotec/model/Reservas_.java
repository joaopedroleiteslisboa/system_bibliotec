package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reservas.class)
public abstract class Reservas_ extends com.system.bibliotec.model.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Reservas, LocalTime> horaRetiradaLivro;
	public static volatile SingularAttribute<Reservas, LocalDate> dataReserva;
	public static volatile SingularAttribute<Reservas, Livro> livro;
	public static volatile SingularAttribute<Reservas, LocalTime> horaReserva;
	public static volatile SingularAttribute<Reservas, Usuario> usuario;
	public static volatile SingularAttribute<Reservas, LocalDate> dataPrevisaoTermino;
	public static volatile SingularAttribute<Reservas, LocalDate> dataRetiradaLivro;
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

