package com.system.bibliotec.service.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FormCancelamentoSolicitacaoLocacao extends FormCancelamentoSolicitacao {


    @NotNull(message = "É necessario informar a solicitação que deseja cancelar")
    private Long idSolicitacao;
    
        
}
