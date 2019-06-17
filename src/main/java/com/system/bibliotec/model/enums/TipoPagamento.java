package com.system.bibliotec.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

	@JsonCreator
	public static TipoPagamento fromValueString(String tipoPagamento) {
		if (tipoPagamento == null) {
			throw new IllegalArgumentException();
		}
		for (TipoPagamento tipoPagamentoSalved : values()) {
			if (tipoPagamento.equals(tipoPagamentoSalved.gettipoPagamento())) {
				return tipoPagamentoSalved;
			}
		}
		throw new IllegalArgumentException();
	}

}
