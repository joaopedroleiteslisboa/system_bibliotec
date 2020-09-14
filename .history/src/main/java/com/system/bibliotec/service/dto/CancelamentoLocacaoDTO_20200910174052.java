package com.system.bibliotec.service.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelamentoLocacaoDTO {
	
	@NotNull(message = "É necessario informar o codigo da Locação")
	private Long idLocacao;


	@NotNull(message = "É necessario informar o codigo do Cliente solicitante")
	private Long idClienteSolicitante;

	

}
