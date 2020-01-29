package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.StatusReserva;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva extends AbstractAuditingEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4258702459699708485L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name = "statusReserva")
	private StatusReserva statusReserva;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaReserva")
	private LocalTime horaReserva;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataReserva")
	private LocalDate dataReserva;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataLimite")
	private LocalDate dataLimite;

	@OneToOne(optional = false)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@OneToOne(optional = false)
	@JoinColumn(name = "idLivro")
	private Livro livro;


}
