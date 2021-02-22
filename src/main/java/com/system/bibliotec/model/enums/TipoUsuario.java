package com.system.bibliotec.model.enums;

public enum TipoUsuario {

    CLIENTE("Cliente"), FUNCIONARIO("Funcionario");

    private String tipoCliente;

    TipoUsuario(String tipoUsuario) {

        this.tipoCliente = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoCliente;
    }
}
