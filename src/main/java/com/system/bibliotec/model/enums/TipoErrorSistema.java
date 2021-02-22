package com.system.bibliotec.model.enums;

public enum TipoErrorSistema {

    EMAIL("EMAIL"),
    TAREFA_AGENDADA("TAREFA AGENDADA"),
    REQUISICAO_REST_TEMPLATE("REQUISICAO REST TEMPLATE"),
    TAREFA_SERVICE("TAREFA SERVICE"),
    NOTIFICACAO_ASSINCRONA("NOTIFICACAO ASSINCRONA"),
    CRIACAO_USUARIO("CRIACAO DE USUARIO"),
    EDICACAO_USUARIO("EDICAO DE USUARIO"),
    NOTIFICACAO_SINCRONA("NOTIFICACAO SINCRONA"),
    EXCECAO_COMUM("EXCECAO COMUN"),
    PROCESSAMENTO_ASSINCRONO("PROCESSAMENTO ASSINCRONO");

    private String tipoError;

    private TipoErrorSistema(String tipoError) {
        this.tipoError = tipoError;
    }


    public String getTipoError() {
        return tipoError;
    }

}
