package com.system.bibliotec.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LocacaoDTO {

	
	@NotNull(message = "Campo livro necessario para realizar uma locação")
	private Long idLivro;

		
	@NotNull(message = "Campo obritatorio para consulta do Solicitante")
	private String cpfUsuarioSolicitante;
	
	@Size(max = 255)
	private String observacoesEntregaLivro;
	

}
