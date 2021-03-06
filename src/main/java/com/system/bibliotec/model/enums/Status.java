package com.system.bibliotec.model.enums;

public enum Status {

    ATRASADA("Atrasada"), ATIVA("Ativa"), CANCELADA("Cancelada"),
    FINALIZADA("Finalizada"), HOMOLOGADA("Homologada"),
    RECUSADA("Recusada"), EM_ANALISE("Em Analise");


    private String status;

    Status(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statusEmprestimo) {
        this.status = status;
    }
}
