package com.system.bibliotec.model.enums;

public enum TipoPessoa {

    FISICA("Fisica"), JURIDICA("Juridica"), NONE("None");

    private String tipoCliente;

    TipoPessoa(String tipoCliente) {

        this.tipoCliente = tipoCliente;
    }

    public String gettipoCliente() {
        return tipoCliente;
    }


    public static TipoPessoa fromValueString(String tipoCliente) {

        if (tipoCliente == null) {
            throw new IllegalArgumentException();
        }

        for (TipoPessoa tipoClienteSalved : TipoPessoa.values()) {
            if (tipoCliente.equalsIgnoreCase(tipoClienteSalved.gettipoCliente())) {
                return tipoClienteSalved;

            }
        }
        throw new IllegalArgumentException();

    }

}
