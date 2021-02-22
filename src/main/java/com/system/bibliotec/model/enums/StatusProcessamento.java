package com.system.bibliotec.model.enums;

import lombok.Data;
import lombok.ToString;


public enum StatusProcessamento {


    PROCESSADO("Processado"),
    EM_PROCESSAMENTO("Em processamento"),
    ERROR_PROCESSAMENTO("Error Processamento"),

    AGUARDANDO_PROCESSAMENTO("Aguardando Processamento"),

    NAO_HABILITADO_A_PROCESSAMENTO("Não habilitado a Processamento");


    private String status;

    StatusProcessamento(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
