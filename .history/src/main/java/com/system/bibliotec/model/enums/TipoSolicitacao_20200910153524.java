package com.system.bibliotec.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum TipoSolicitacao {

    RESERVA("Reserva"), LOCACAO("Locacao");


    private String tipoSolicitacao;



    
}