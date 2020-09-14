package com.system.bibliotec.repository.filter;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.TipoSolicitacao;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SolicitacaoFilter {
 
    

    private Long idSolicitacao;

	private Long idUsuario;

	private Long idExemplar;

	private String createdBy;  // anonymous user online...

    private Status status;
    
    private boolean rejeitado;
    
    private TipoSolicitacao tipo;
	
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime horaSolicitacaoInicio;

		
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime horaSolicitacaoFim;


	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataSolicitacaoInicio;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dataSolicitacaoFim;
}