package com.system.bibliotec.service.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevolucaoLocacaoDTO {

    @NotNull(message = "É necessario informar o codigo da Locação")
    private Long idLocacao;

    private String observacoesDevolucao;

    @NotNull(message = "É necessario informar o codigo do Cliente Solicitante")
    private Long idClienteSolicitante;

}

