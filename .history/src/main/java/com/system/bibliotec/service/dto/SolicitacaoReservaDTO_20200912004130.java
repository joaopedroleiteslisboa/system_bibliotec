package com.system.bibliotec.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.system.bibliotec.model.Livro;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SolicitacaoReservaDTO {
	
	@NotNull(message = "É necessario informa um Livro para Reservar")
	private Long idLivro;


	private Long idClienteCheckin; //em caso de atendimento pelo administrador do sistema ou atendente do recinto

	
    private LocalDate dataRetiradaExemplar;

    
    private LocalTime horaRetiradaExemplar;


}
