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
 
    


    public SolicitacaoFilter(Long idSolicitacao, Long idUsuario, Long idExemplar, String createdBy, Status status,
    boolean rejeitado, TipoSolicitacao tipo, LocalTime horaSolicitacaoInicio, LocalTime horaSolicitacaoFim,
    LocalDate dataSolicitacaoInicio, LocalDate dataSolicitacaoFim) {
        this.idSolicitacao = idSolicitacao;
        this.idUsuario = idUsuario;
        this.idExemplar = idExemplar;
        this.createdBy = createdBy;
        this.status = status;
        this.rejeitado = rejeitado;
        this.tipo = tipo;
        this.horaSolicitacaoInicio = horaSolicitacaoInicio;
        this.horaSolicitacaoFim = horaSolicitacaoFim;
        this.dataSolicitacaoInicio = dataSolicitacaoInicio;
        this.dataSolicitacaoFim = dataSolicitacaoFim;
}



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