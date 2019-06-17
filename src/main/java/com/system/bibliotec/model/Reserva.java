package com.system.bibliotec.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.StatusReserva;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "reservas")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Long id;
	
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
	private Cliente idCliente;

	@OneToOne(optional = false)
	@JoinColumn(name = "idLivro")
	private Livro idLivro;


}
