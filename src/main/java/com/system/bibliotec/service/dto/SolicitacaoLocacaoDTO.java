package com.system.bibliotec.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SolicitacaoLocacaoDTO {


    @NotNull(message = "É necessario informa um Livro para Locar um exemplar")
    private Long idLivro;


    private Long idUsuarioAnonimo;


    private LocalDate dataRetiradaExemplar;


    private LocalTime horaRetiradaExemplar;


}
