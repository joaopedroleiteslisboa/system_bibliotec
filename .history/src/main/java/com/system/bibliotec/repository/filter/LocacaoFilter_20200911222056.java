package com.system.bibliotec.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class LocacaoFilter {


	
	
	private Long idLocacao;

	private Long idExemplar;

	private Long idUsuario;
	

	@Enumerated(EnumType.STRING)
	private Status statusLocacao;

	@JsonFormat(pattern = "hh:mm:ss")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime horaLocacaoInicio;

	@JsonFormat(pattern = "hh:mm:ss")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime horaLocacaoFim;

	
	@JsonFormat(pattern = "hh:mm:ss")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime horaCancelamentoLocacaoInicio;

	@JsonFormat(pattern = "hh:mm:ss")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime horaCancelamentoLocacaoFim;


	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLocacaoInicio;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLocacaoFim;


		
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCancelamentoLocacaoInicio;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCancelamentoLocacaoFim;


	

}
