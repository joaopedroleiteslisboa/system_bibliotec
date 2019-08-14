package com.system.bibliotec.model;

import com.system.bibliotec.model.enums.StatusReserva;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reserva.class)
public abstract class Reserva_ {

	public static volatile SingularAttribute<Reserva, StatusReserva> statusReserva;
	public static volatile SingularAttribute<Reserva, LocalDate> dataReserva;
	public static volatile SingularAttribute<Reserva, Cliente> cliente;
	public static volatile SingularAttribute<Reserva, Livro> livro;
	public static volatile SingularAttribute<Reserva, LocalTime> horaReserva;
	public static volatile SingularAttribute<Reserva, LocalDate> dataLimite;
	public static volatile SingularAttribute<Reserva, Long> id;

}

