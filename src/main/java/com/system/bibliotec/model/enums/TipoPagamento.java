package com.system.bibliotec.model.enums;

public enum TipoPagamento {

	DINHEIRO("Dinheiro"), CARTAO_CREDITO("Carta de Credito"), CARTAO_DEBITO("Carta de Debito");

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
