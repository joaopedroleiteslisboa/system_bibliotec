package com.system.bibliotec.repository.filter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.security.AuthoritiesConstantsUltis;
import com.system.bibliotec.security.SecurityUtils;

@Getter
@Setter
@ToString
public class ReservaFilter {


//check type user in context...
this.createdBy = ( SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_ADMIN) || 
SecurityUtils.isCurrentUserInRole(AuthoritiesConstantsUltis.ROLE_USER_SYSTEM) )?  "testtando para apagar": SecurityUtils.getCurrentUserLoginToOperacoes().orElse("");


	


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
