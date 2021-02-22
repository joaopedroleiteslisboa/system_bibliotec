package com.system.bibliotec.model.enums;

public enum TipoPagamento {


    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    CHEQUE("Cheque"),
    BOLETO_BANCARIO("Boleto Bancário"),
    DEPOSITO_BANCARIO("Depósito Bancário");

    private String tipoPagamento;

    TipoPagamento(String tipoPagamento) {

        this.tipoPagamento = tipoPagamento;
    }

    public String gettipoPagamento() {
        return tipoPagamento;
    }

    public void settipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }


}
