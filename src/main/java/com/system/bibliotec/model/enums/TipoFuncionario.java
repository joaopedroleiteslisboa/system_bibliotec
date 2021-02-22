package com.system.bibliotec.model.enums;

public enum TipoFuncionario {

    ATENDENTE("Atendente"),

    BIBLIOTECARIO("Bibliotecario"),

    DOCENTE("Docente");

    private String tipoFuncionariodo;

    TipoFuncionario(String tipoFuncionariodo) {

        this.tipoFuncionariodo = tipoFuncionariodo;
    }

    public String gettipoFuncionariodo() {
        return tipoFuncionariodo;
    }


}
