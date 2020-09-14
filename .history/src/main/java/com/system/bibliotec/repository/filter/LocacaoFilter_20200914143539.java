package com.system.bibliotec.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.SecurityUtils;
import com.system.bibliotec.service.UserService;

import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class LocacaoFilter {


	

	
	public LocacaoFilter(String createdBy, Long idLocacao, Long idExemplar, Long idUsuario, Status statusLocacao,
			LocalTime horaLocacaoInicio, LocalTime horaLocacaoFim, LocalTime horaCancelamentoLocacaoInicio,
			LocalTime horaCancelamentoLocacaoFim, LocalDate dataLocacaoInicio, LocalDate dataLocacaoFim,
			LocalDate dataCancelamentoLocacaoInicio, LocalDate dataCancelamentoLocacaoFim) {
		
		//check type user in context...
		if(idUsuario == null){
					
		    this.createdBy = ( SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || 
			
						SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM) )?  null: SecurityUtils.getCurrentUserLoginToOperacoes().get();
		}else{

			if( SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || 			
					SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM)){
						this.idUsuario = idUsuario;		
					}
			
		}
		
		this.idLocacao = idLocacao;
		this.idExemplar = idExemplar;		
		this.statusLocacao = statusLocacao;
		this.horaLocacaoInicio = horaLocacaoInicio;
		this.horaLocacaoFim = horaLocacaoFim;
		this.horaCancelamentoLocacaoInicio = horaCancelamentoLocacaoInicio;
		this.horaCancelamentoLocacaoFim = horaCancelamentoLocacaoFim;
		this.dataLocacaoInicio = dataLocacaoInicio;
		this.dataLocacaoFim = dataLocacaoFim;
		this.dataCancelamentoLocacaoInicio = dataCancelamentoLocacaoInicio;
		this.dataCancelamentoLocacaoFim = dataCancelamentoLocacaoFim;
	}
	


	
	private String createdBy;  // anonymous user online...

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
