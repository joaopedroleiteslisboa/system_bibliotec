package com.system.bibliotec.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum TipoSolicitacao {

    RESERVA("Reserva"),
     LOCACAO("Locacao"),
      MUDANCA_DOCUMENTO("Mudancao_Documento"), 
      OUTROS("Outros");


    private String tipoSolicitacao;



    
}