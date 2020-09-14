package com.system.bibliotec.repository.filter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;

@Getter
@Setter
@ToString
public class ReservaFilter {





	public ReservaFilter(Long idReserva, Long idUsuario, Long idExemplar, String createdBy, Status status,
			LocalTime horaReserva, LocalDate dataReservaInicio, LocalDate dataReservaFim,
			LocalDate dataPrevisaoTermino) {
		this.idReserva = idReserva;
		this.idUsuario = idUsuario;
		this.idExemplar = idExemplar;
		this.createdBy = createdBy;
		this.status = status;
		this.horaReserva = horaReserva;
		this.dataReservaInicio = dataReservaInicio;
		this.dataReservaFim = dataReservaFim;
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}




	private Long idReserva;

	private Long idUsuario;

	private Long idExemplar;

	private String createdBy;  // anonymous user online...

	private Status status;
	
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime horaReserva;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReservaInicio;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReservaFim;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataPrevisaoTermino;




	
}
