package com.system.bibliotec.repository.filter;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SolicitacaoFilter {
 
    

    private Long idReserva;

	private Long idUsuario;

	private Long idExemplar;

	private String createdBy;  // anonymous user online...

	private Status status;
	
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime horaReservaInicio;

		
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime horaReservaFim;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReservaInicio;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataReservaFim;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataPrevisaoTermino;


}